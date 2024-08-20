
package com.ngnydevices.open.a9000p;

import com.ngnydevices.open.a9000p.sims.ATTA_Simulator;
import com.ngnydevices.open.a9000p.sims.Common;
import com.ngnydevices.open.a9000p.sims.LIS_Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class LisApp {

    private static final BasicLogger log = new BasicLogger(LisApp.class.getName());

    private static JButton bStart;
    private static JButton bStop;
    private static AstmThread lis;
    private static JButton bClear;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("LIS Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);

        // Create the text area that will serve as the console
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 10));
        // Redirect System.out to the text area
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);

        // Create a simple panel for the top part of the split pane
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());


        JPanel server = new JPanel(new BorderLayout());
        server.setBorder(BorderFactory.createTitledBorder("TCP SERVICE"));

        JPanel serverOptions = new JPanel();
        serverOptions.setLayout(new BoxLayout(serverOptions, BoxLayout.Y_AXIS));

        JTextArea workorders = new JTextArea();
        workorders.setEditable(true); // Make it read-only
        JScrollPane workordersScrollPane = new JScrollPane(workorders);
        workorders.setFont(new Font("Courier New", Font.PLAIN, 10));

        // Redirect System.out to the text area

        workorders.setText("123456789:TEST1,TEST2\nS0001:TEST3");

        JPanel tools = new JPanel(new FlowLayout(FlowLayout.LEADING));
        bStart = new JButton("Start");
        bStop = new JButton("Stop");
        bClear = new JButton("Clear console");
        tools.add(bStart);
        tools.add(bStop);
        tools.add(bClear);

        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });


        JTextField host = addLabelAt(serverOptions, "TCP Connecting host", "127.0.0.1");
        JTextField port = addLabelAt(serverOptions, "TCP Connecting port", "55557");
        serverOptions.add(tools);
        server.add(serverOptions, BorderLayout.CENTER);


        JPanel getTests = new JPanel(new BorderLayout());
        getTests.setBorder(BorderFactory.createTitledBorder("SAMPLE/TEST"));
        getTests.add(workordersScrollPane, BorderLayout.CENTER);


        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(server);
        main.add(getTests);

        topPanel.add(main, BorderLayout.CENTER);

        // Create the split pane and add the top panel and the text area
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, scrollPane);
        splitPane.setDividerLocation(300); // Initial position of the divider
        splitPane.setResizeWeight(0.5); // Distribute space equally initially

        // Add the split pane to the frame
        frame.add(splitPane);

        // Make the frame visible
        frame.setVisible(true);

        // Example output to the console


        bStop.setEnabled(false);
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bStart.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        log.info("Service started");
                        lis = new AstmThread(false, host.getText(), Integer.parseInt(port.getText()));
                        lis.setListener((tcp, q) -> onReceivedPayload(workorders.getText(), tcp, q));
                        lis.start();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                bStop.setEnabled(true);
                            }
                        });
                    }
                }).start();

            }
        });

        bStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bStop.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lis.kill();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                bStart.setEnabled(true);
                            }
                        });

                    }
                }).start();
            }
        });


    }

    private static void onReceivedPayload(String text, TcpStateMachine tcp, Payload receivedPayload) {


        if (receivedPayload.getFrames().size() == 0) {
            log.info("ASTM low level ping detected");
            return;
        }

        if (receivedPayload.getFrames().size() == 2) {
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

        for (Frame f : receivedPayload.getFrames()) {
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

            StringBuilder pTests = new StringBuilder();
            for (String l : text.split("\n")) {
                if (l.startsWith(sampleId + ":")) {
                    String tmp1 = l.split(":")[1].trim();
                    for (String tmp2 : tmp1.split(",")) {

                        if (pTests.length() > 0) pTests.append("\\");
                        pTests.append("^^^" + tmp2);

                    }

                }
            }
            order.setTests(pTests.toString());


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

    private static JTextField addLabelAt(JPanel getTestOptions, String sampleId, String defValue) {
        JLabel myLabel = new JLabel(sampleId);
        //myLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JTextField myTextField = new JTextField(defValue);


        myTextField.setColumns(12);
        Dimension pd = new Dimension(120, 30);
        myLabel.setMaximumSize(pd);
        myLabel.setSize(pd);
        myLabel.setPreferredSize(pd);
        myLabel.setMinimumSize(pd);
        //myTextField.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(myLabel, BorderLayout.WEST);
        panel.add(myTextField, BorderLayout.CENTER);


        getTestOptions.add(panel);
        return myTextField;
    }

}