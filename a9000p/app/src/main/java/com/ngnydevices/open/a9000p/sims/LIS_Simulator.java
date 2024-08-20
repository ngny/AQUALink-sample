package com.ngnydevices.open.a9000p.sims;

import com.ngnydevices.open.a9000p.*;

import java.util.ArrayList;

public class LIS_Simulator {


    private final static BasicLogger log = new BasicLogger(LIS_Simulator.class.getName());


    public static void onReceivedPayload(TcpStateMachine tcp, Payload receivedPayload) {


        if (receivedPayload.getFrames().size()==0) {
            log.info("ASTM low level ping detected");
            return;
        }

        if (receivedPayload.getFrames().size() ==2) {
            if (receivedPayload.getFrames().get(0).getRecordType() != Frame.RecordType.Header) {
                if (receivedPayload.getFrames().get(1).getRecordType() != Frame.RecordType.Terminator) {
                    log.info("ASTM high level ping detected");
                    return;
                }
            }
        }

        if (receivedPayload.getFrames().size() < 3) {
            log.warn("not enough frames: " + receivedPayload.getFrames().size());
            return;
        }
        if (receivedPayload.getFrames().get(0).getRecordType() != Frame.RecordType.Header) {
            log.warn("first frame is not header");
            return;
        }

        ArrayList<Frame> testOrderF = new ArrayList<>();
        ArrayList<Frame> patientF = new ArrayList<>();
        ArrayList<Frame> requestF = new ArrayList<>();
        ArrayList<Frame> resultF = new ArrayList<>();

        if (receivedPayload.getFrames().getLast().getRecordType() != Frame.RecordType.Terminator) {
            log.warn("last frame is not terminator");
            return;
        }



        for(Frame f : receivedPayload.getFrames()) {
            log.info("Frame " + ASTM.niceByteRepresentation(f.getParsedData()));
        }


        for (Frame f : receivedPayload.getFrames()) {
            switch (f.getRecordType()) {
                case PatientInformation:
                    patientF.add(f);
                    break;
                case RequestInformation:
                    requestF.add(f);
                    break;
                case TestOrder:
                    testOrderF.add(f);
                    break;
                case ResultRecord:
                    resultF.add(f);
                    break;
            }
        }

        if (testOrderF.size() == 1 && patientF.size() == 1 && resultF.size() > 0 && requestF.size() == 0) {

            log.info("============ SEND RESULTS REQUEST ============");

            String[] aux = testOrderF.get(0).getSpecimenId().split("\\^", -1);
            String sampleId = aux[1];
            log.info("sample:" + sampleId);

            for (Frame f : resultF) {

                if (f.getTestName() == null) continue;

                if (f.getTestName().equalsIgnoreCase("PRIMARY_T")) {

                    if ("SUCCESS".equals(f.getResultStatus())) {
                        log.info("Primary sample placed successfully at location:" + f.getLocation());
                    } else {
                        log.info("Primary sample placed with error at location:" + f.getLocation());
                    }

                } else if (f.getTestName().startsWith("SECONDARY_T_")) {

                    log.info("Secondary sample details");

                } else {


                    if ("OK".equals(f.getLocation())) {
                        log.info("Test [" + f.getTestName() + "] processed successfully");
                    } else {
                        log.info("Test [" + f.getTestName() + "] processed with error: " + f.getLocation());
                    }

                }


            }



            return;
        } else if (requestF.size() == 1 && testOrderF.size() == 0 && patientF.size() == 0 && resultF.size() == 0) {

            log.info("============ GET TESTS REQUEST ============");

            Frame f = (requestF.get(0));
            String specimen = f.getSpecimenId();
            String[] aux = specimen.split("\\^", -1);
            String sampleId = aux[1];

            log.info("GET_TEST request from sample " + sampleId + " at rack " + aux[2] + " and hole " + aux[3]);

            Payload p = Frame.getStandardPayload("LIS-A2", "A9000P");
            Frame patient = new Frame(Frame.RecordType.PatientInformation);

            patient.setPatientIdentifier("PATIENT_ID");
            patient.setPatientFirstName("コンニチハ");
            patient.setPatientMiddleName("SIR");
            patient.setPatientLastName("NEWTON");
            patient.setIndex(1);
            p.add(patient);
            Frame order = new Frame(Frame.RecordType.TestOrder);
            order.setIndex(1);
            order.setSpecimenId(specimen);
            order.setTests("^^^T1\\^^^T2\\^^^T3");
            order.setPriority("R");
            order.setActionCode("");
            order.setReportType("");
            p.add(order);
            p.add(new Frame(Frame.RecordType.Terminator, "F"));

            try {
                tcp.pushPayload(p, "SENDING_GET_TEST_RESPONSE", Common.ASTM_HIGH_LEVEL_TIMEOUT_MS);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            return;
        }

        log.warn("unknown payload");
    }
}
