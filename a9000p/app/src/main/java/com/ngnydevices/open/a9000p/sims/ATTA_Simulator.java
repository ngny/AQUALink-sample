package com.ngnydevices.open.a9000p.sims;

import com.ngnydevices.open.a9000p.*;
import com.ngnydevices.open.a9000p.errors.DataTransmissionException;
import com.ngnydevices.open.a9000p.errors.TimeoutException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ATTA_Simulator {

    private final static BasicLogger log = new BasicLogger(ATTA_Simulator.class.getName());

    private static final String PRIMARY_TUBE_TEST_NAME = "PRIMARY_T";
    private static final String SECONDARY_TUBE_TEST_NAME = "SECONDARY_T";


    public static void getTests(AstmThread atta, String sampleId, String rackId, String locationId) throws Exception, TimeoutException, DataTransmissionException {


        Payload tmp = Frame.getStandardPayload("A9000P", "LIS-A2");
        Frame query = new Frame(Frame.RecordType.RequestInformation);
        query.setSpecimenId(
                sampleId + "^" + rackId + "^" + locationId);
        tmp.add(query);
        tmp.add(new Frame(Frame.RecordType.Terminator));


        Payload response = atta.request(tmp, "GET_TEST_REQUEST", Common.ASTM_HIGH_LEVEL_TIMEOUT_MS, Common.ASTM_HIGH_LEVEL_TIMEOUT_MS);


        for (Frame f : response.getFrames()) {

            if (f.getRecordType() == Frame.RecordType.PatientInformation) {
                log.info("Patient:" + f.getPatientLastName() + ", " + f.getPatientFirstName() + " " + f.getPatientMiddleName());
            }

            if (f.getRecordType() == Frame.RecordType.TestOrder) {
                String[] parts = f.getTests().split("\\\\", -1);
                for (String s : parts) {

                    if (s.length() > 3) {
                        String test = s.equalsIgnoreCase("NOT_FOUND") ? s : s.substring(3);

                        log.info("Test received:" + test);
                    }
                }
            }
        }










    }

    public static void sendResults(AstmThread atta, String sampleId,
                                String outputRack, String outputLocation) throws Exception, TimeoutException, DataTransmissionException {




        log.info("Now the sample has been sorted to the destination rack and an aliquot has been generated");

        Payload tmp = Frame.getStandardPayload("A9000P", "LIS-A2");
        Frame f = new Frame(Frame.RecordType.PatientInformation);
        tmp.add(f);
        f = new Frame(Frame.RecordType.TestOrder);
        f.setSpecimenId(sampleId + "^" + outputRack + "^" + outputLocation);
        f.setRackId(outputRack);
        f.setRackPosition(outputLocation);
        f.setPriority("R");
        f.setReportType("F");
        tmp.add(f);

        StringBuilder buf = new StringBuilder();
        {
            String tn = PRIMARY_TUBE_TEST_NAME;
            Frame g = new Frame(Frame.RecordType.ResultRecord);
            g.setTestName("^^^" + tn + "^^^^");

            g.setFlags("");
            g.setFlags("Success");
            g.setResultStatus(outputRack + "_" + outputLocation);
            g.setRLU(false);
            g.setDateTestCompleted(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            tmp.add(g);

            if (buf.length() > 0) buf.append("\\");
            buf.append("^^^").append(tn);


        }

        {

            Frame g = new Frame(Frame.RecordType.ResultRecord);
            g.setTestName("^^^" + "T1" + "^^^^");
            g.setFlags("F");
            g.setResultStatus("OK");
            g.setRLU(false);
            g.setDateTestCompleted(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            tmp.add(g);

            if (buf.length() > 0) buf.append("\\");
            buf.append("^^^").append("T1");

            g = new Frame(Frame.RecordType.ResultRecord);
            g.setTestName("^^^" + "T2" + "^^^^");
            g.setFlags("F");
            g.setResultStatus("ERROR");
            g.setRLU(false);
            g.setDateTestCompleted(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            tmp.add(g);

            if (buf.length() > 0) buf.append("\\");
            buf.append("^^^").append("T2");

        }


        {
            int idx = 1;
            String tn = SECONDARY_TUBE_TEST_NAME + "_" + idx;
            Frame g = new Frame(Frame.RecordType.ResultRecord);
            g.setTestName("^^^" + tn + "^^^^");


            if (buf.length() > 0) buf.append("\\");
            buf.append("^^^").append(tn);

            g.setFlags("SUCCESS");
            g.setResultStatus("ALIQUOTERACK_1" + "_" + "C1");
            g.setSpecimenId("001" + sampleId);
            g.setRLU(false);
            g.setDateTestCompleted(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
            tmp.add(g);
        }

        f.setTests(buf.toString());
        tmp.add(new Frame(Frame.RecordType.Terminator));
        atta.request(tmp,"SEND_RESULTS", Common.ASTM_HIGH_LEVEL_TIMEOUT_MS, -1);


    }
}
