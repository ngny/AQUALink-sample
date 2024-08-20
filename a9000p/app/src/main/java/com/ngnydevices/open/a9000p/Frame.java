package com.ngnydevices.open.a9000p;



import com.ngnydevices.open.a9000p.errors.InvalidArgumentsException;
import com.ngnydevices.open.a9000p.errors.NotEnoughFieldsException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class Frame {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public String extraInfo;
    private RecordType recordType;
    private int index;
    private int count;
    private Result result = Result.NotSent;
    private String senderName;
    private Payload payload;
    private String specimenId;
    private String dateTestCompleted;
    private String resultStatus;
    private String flags;
    private String testName;
    private String sampleId;
    private String rackId;
    private String rackPosition;
    private String receiverName;
    private MessageType messageType = MessageType.Unknown;
    private String identification;
    private String dmTests;
    private String action;
    private String samplingInformations;
    private String sampleProcessingId;
    private String response;
    private String statusType;
    private String[] fields;
    private byte[] parsedData;
    private boolean useDate;
    private String tests;
    private String priority;
    private String actionCode;
    private String reportType;
    private String terminationCode;
    private String patientIdentifier;
    private String patientLastName;
    private String patientFirstName;
    private String patientMiddleName;
    private String birthDate;
    private String sex;
    private String physician;
    private String location;

    public Frame() {

    }
    public Frame(RecordType header) {
        recordType = header;
    }
    public Frame(RecordType header, String termination) {
        recordType = header;
        terminationCode = termination;
    }

    public static byte[] getDataLinkBytes(int frameNumberTx, byte[] data, boolean isLast) {

        int iChkSum = 0;

        int j = 0;
        byte[] a = new byte[data.length + 7 + (isLast ? 1 : 0)];
        a[j++] = ASTM.STX;
        a[j] = (byte) (frameNumberTx);
        iChkSum += a[1];
        j++;

        for (int i = 0; i < data.length; i++, j++) {
            a[j] = data[i];
            iChkSum += data[i];


        }

        if (isLast) {
            a[j] = ASTM.CR;
            iChkSum += a[j];
            j++;
            a[j] = ASTM.ETX;
        } else {
            a[j] = ASTM.ETB;
        }
        iChkSum += a[j];
        j++;

        iChkSum = (iChkSum & 0xFF);
        char iChkLo = ASTM.hexArray[iChkSum % 16];
        char iChkHi = ASTM.hexArray[iChkSum / 16];

        a[j++] = (byte) (iChkHi);
        a[j++] = (byte) (iChkLo);
        a[j++] = ASTM.CR;
        a[j] = ASTM.LF;
        return a;
    }

    public static String nullSpace(String x) {
        if (x == null) return "";
        else return x;
    }

    public static Payload getStandardPayload(String sender, String receiver) {
        Payload p = new Payload();

        Frame f = new Frame(RecordType.Header);
        f.setSenderName(sender);
        f.setReceiverName(receiver);
        f.setSampleProcessingId("P");

        p.add(f);
        return p;
    }



    private String getDateTestCompleted() {
        return dateTestCompleted;
    }

    public void setDateTestCompleted(String dateTestCompleted) {
        this.dateTestCompleted = dateTestCompleted;
    }

    private String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    private String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    private String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    private String getRackPosition() {
        return rackPosition;
    }

    public void setRackPosition(String rackPosition) {
        this.rackPosition = rackPosition;
    }

    public void setSampleProcessingId(String sampleProcessingId) {
        this.sampleProcessingId = sampleProcessingId;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public boolean isLast() {
        return index == count - 1;
    }

    public void parseWithoutHeader(byte[] data) throws Exception {

        setParsedData(data);

        if (data == null) {
            throw new InvalidArgumentsException();
        } else {

            if (data.length == 0) {
                throw new InvalidArgumentsException();
            }

            switch (data[0]) {

                case (byte) 'H':

                    this.setRecordType(RecordType.Header);
                    processHeader(data);
                    break;

                case (byte) 'O':
                    this.setRecordType(RecordType.TestOrder);
                    processOrder(data);
                    break;

                case (byte) 'R':
                    this.setRecordType(RecordType.ResultRecord);
                    processResult(data);
                    break;

                case (byte) 'Q':
                    this.setRecordType(RecordType.RequestInformation);
                    processRequest(data);
                    break;

                case (byte) 'M':
                    this.setRecordType(RecordType.MessageManufacturer);
                    processManufacturerMessage(data);
                    break;


                case (byte) 'P':
                    this.setRecordType(RecordType.PatientInformation);
                    processPatientInformationMessage(data);
                    break;

                case (byte) 'C':
                    this.setRecordType(RecordType.ResultDetail);
                    processResultDetail(data);
                    break;

                case (byte) 'L':
                    this.setRecordType(RecordType.Terminator);
                    processTerminator(data);
                    break;

                default:

                    this.setRecordType(RecordType.Unknown);
            }

        }
    }

    private void processResultDetail(byte[] w) throws NotEnoughFieldsException {


        String[] tmp = new String(w).split("\\|", -1);
        if (tmp.length < 5) {
            throw new NotEnoughFieldsException();
        }

        setPriority(tmp[2]);
        setLocation(tmp[3]);
        setFlags(tmp[4]);
    }

    private void processPatientInformationMessage(byte[] w) {

        String[] tmp = new String(w).split("\\|", -1);

        if (tmp.length >= 3) setPatientIdentifier(tmp[2]);
        if (tmp.length >= 6) {
            setPatientIdentifier(tmp[2]);
            String[] aux = tmp[5].split("\\^", -1);
            setPatientLastName(aux[0]);
            setPatientFirstName(aux.length >= 2 ? aux[1] : "");
            setPatientMiddleName("");
            if (tmp.length >= 8) setBirthDate(tmp[7]);
            if (tmp.length >= 9) setSex(tmp[8]);
            setPhysician(tmp.length >= 14 ? tmp[13] : "");
            setFields(tmp);
        }


    }

    private void processManufacturerMessage(byte[] w) throws NotEnoughFieldsException {


        String[] tmp = new String(w).split("\\|", -1);
        if (tmp.length < 3) {
            throw new NotEnoughFieldsException();
        }

        messageType = MessageType.Unknown;
        messageType = MessageType.valueOf(tmp[2]);

        switch (messageType) {

            case Unknown:
                break;
            case STATUS:
                if (tmp.length < 5) {
                    throw new NotEnoughFieldsException();
                }
                statusType = tmp[3];
                extraInfo = tmp[4].trim();
                break;



        }


    }

    private void processRequest(byte[] w) throws Exception {

        String[] tmp = new String(w).split("\\|");


        if (tmp.length < 3) {
            throw new NotEnoughFieldsException();
        }

        this.setIndex(Integer.parseInt(tmp[1]));
        this.setSpecimenId(tmp[2]);
    }

    private void setSpecimenComponents(String specimenId) {
        this.setSampleId(specimenId);
    }

    private void processOrder(byte[] w) {

        String[] tmp = new String(w).split("\\|", -1);

        setOrderFields(tmp);

        if (tmp.length >= 3) {
            this.setIndex(Integer.parseInt(tmp[1]));
            this.setSpecimenId(tmp[2]);
            setTests(tmp[4]);
        }


    }

    private void processResult(byte[] w) throws NotEnoughFieldsException {
        String[] tmp = new String(w).split("\\|", -1);


        if (tmp.length < 13) {
            throw new NotEnoughFieldsException();
        }

        this.setIndex(Integer.parseInt(tmp[1]));

        String universalTestId = tmp[2];
        String[] parts = universalTestId.split("\\^\\^\\^|\\^\\^\\^\\^");

        this.setTestName(parts[1]);
        this.setLocation(tmp[3]);

        if (tmp[3].equals("OK")) {
            this.result = Result.Success;
            this.setResult(this.getResult());
        }

        this.setFlags(tmp[6]);

        this.setResultStatus(tmp[8]);

        this.setDateTestCompleted(tmp[12]);

    }

    private void processTerminator(byte[] w) throws Exception {

        String[] tmp = new String(w).split("\\|", -1);


        if (tmp.length < 3) {
            throw new NotEnoughFieldsException();
        }
        this.setIndex(Integer.parseInt(tmp[1]));

    }

    private void processHeader(byte[] w) throws Exception {

        String[] tmp = new String(w).split("\\|", -1);


        if (tmp.length < 5) {
            throw new NotEnoughFieldsException();
        }

        senderName = tmp[4];

        receiverName = "--";
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    private void addString(ArrayList<Byte> data, String string1) {

        if (string1 == null) {
            return;
        }

        String string = string1.replace("\n", "").replace("\r", "");
        byte[] d = string.getBytes();
        for (byte aD : d) {
            data.add(aD);
        }
    }

    public byte[] generate() {

        ArrayList<Byte> data = new ArrayList<>();

        switch (this.getRecordType()) {

            case Header:
                generateHeader(data);
                break;

            case TestOrder:
                generateOrder(data);
                break;

            case ResultRecord:
                generateResult(data);
                break;

            case RequestInformation:
                generateRequest(data);
                break;

            case MessageManufacturer:
                generateManufacturerMessage(data);
                break;

            case Terminator:
                generateTerminator(data);
                break;

            case ResultDetail:
                generateResultDetail(data);
                break;

            case PatientInformation:
                generatePatient(data);
                break;
            case Unknown:


            default:
                break;

        }

        byte[] r = new byte[data.size()];

        int j = 0;
        for (Byte aData : data) {
            r[j++] = aData;
        }


        return r;
    }

    private void generateResultDetail(ArrayList<Byte> data) {

        data.add((byte) 'C');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, getPriority());
        data.add((byte) '|');
        addString(data, getLocation());
        data.add((byte) '|');
        addString(data, getFlags());


    }

    private void generatePatient(ArrayList<Byte> data) {
        data.add((byte) 'P');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, this.getPatientIdentifier());
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, nullSpace(this.getPatientLastName()) + "^" + nullSpace(this.getPatientFirstName()) + "^" + nullSpace(this.getPatientMiddleName()));
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.getBirthDate());
        data.add((byte) '|');
        addString(data, this.getSex());
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.getPhysician());
        String c = "||||||||||||";
        byte[] b = c.getBytes();
        for (byte aB : b) {
            data.add(aB);
        }
        addString(data, this.location);
    }

    private void generateTerminator(ArrayList<Byte> data) {

        data.add((byte) 'L');
        data.add((byte) '|');
        addString(data, "1");
        data.add((byte) '|');
        if (getTerminationCode() != null && getTerminationCode().equals("F"))
            data.add((byte) 'F');
        else
            data.add((byte) 'N');

    }

    private void generateManufacturerMessage(ArrayList<Byte> data) {

        data.add((byte) 'M');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, messageType.toString());
        data.add((byte) '|');


        if (Objects.requireNonNull(messageType) == MessageType.STATUS) {
            addString(data, statusType);
            data.add((byte) '|');
            addString(data, extraInfo);
        }



    }

    private void generateResult(ArrayList<Byte> data) {

        data.add((byte) 'R');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, getTestName());
        data.add((byte) '|');
        addString(data, getResultStatus());
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, getPriority());
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, getFlags());
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, getDateTestCompleted());
    }

    private void generateRequest(ArrayList<Byte> data) {

        data.add((byte) 'Q');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, "^" + nullSpace(this.getSampleId()) + "^" + nullSpace(this.getRackId()) + "^" + nullSpace(this.getRackPosition()));
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        if (extraInfo != null) {
            addString(data, extraInfo);
        }
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) 'O');
    }

    private void generateOrder(ArrayList<Byte> data) {

        data.add((byte) 'O');
        data.add((byte) '|');
        addString(data, Integer.toString(this.getIndex()));
        data.add((byte) '|');
        addString(data, nullSpace(this.getSampleId()) + "^" + nullSpace(this.getRackId()) + "^" + nullSpace(this.getRackPosition()));
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.getTests());
        data.add((byte) '|');
        addString(data, this.getPriority());
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.getDate());
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.getActionCode());
        String c = "||||||||||||||";
        byte[] b = c.getBytes();
        for (byte aB : b) {
            data.add(aB);
        }
        addString(data, this.getReportType());


    }

    private void generateHeader(ArrayList<Byte> data) {

        data.add((byte) 'H');
        String ans = getSenderName();
        if (ans == null) {
            ans = "LIS";
        } else if (ans.endsWith("null")) {
            ans = "LIS";
        }

        addString(data, "|\\^&|||" + ans);
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, receiverName);
        data.add((byte) '|');
        data.add((byte) '|');
        addString(data, this.sampleProcessingId);
        data.add((byte) '|');
        addString(data, "LIS2-A2" );
        data.add((byte) '|');
        addString(data, getDate());
    }

    private String getDate() {
        return useDate ? dateFormat.format(new Date()) : "";
    }

    public RecordType getRecordType() {
        return recordType;
    }

    private void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public String getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(String specimenId) {
        this.specimenId = specimenId;
        this.setSpecimenComponents(this.getSpecimenId());
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private String getTerminationCode() {
        return terminationCode;
    }

    public String[] getFields() {
        return fields;
    }

    private void setFields(String[] fields) {
        this.fields = fields;
    }

    public String optString(int i) {

        if (fields == null) return null;
        if (fields.length <= i) return null;
        return fields[i];

    }

    public void setRLU(boolean rlu) {
    }

    public void setOrderFields(String[] orderFields) {
    }

    public byte[] getParsedData() {
        return parsedData;
    }

    public void setParsedData(byte[] parsedData) {

        this.parsedData = parsedData;
    }


    public enum RecordType {
        Header, PatientInformation,
        RequestInformation, TestOrder, Terminator, MessageManufacturer, ResultRecord, ResultDetail, Unknown
    }


}
