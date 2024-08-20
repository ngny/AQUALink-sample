
package com.ngnydevices.open.a9000p;

import com.ngnydevices.open.a9000p.sims.ATTA_Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;

public class InstrumentApp {

    private static final BasicLogger log = new BasicLogger(InstrumentApp.class.getName());

    private static JButton bStart;
    private static JButton bStop;
    private static JButton bGet;
    private static JButton bSend;
    private static JTextField outputHoleId;
    private static JTextField outputRackId;
    private static AstmThread atta;
    private static JButton bClear;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("CUBE / ATTA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 900);

        // Create the text area that will serve as the console
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Courier New",Font.PLAIN, 10));
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


        JTextField port = addLabelAt(serverOptions, "TCP Listening port", "55557");
        serverOptions.add(tools);
        server.add(serverOptions, BorderLayout.CENTER);






        JPanel getTests = new JPanel(new BorderLayout());
        getTests.setBorder(BorderFactory.createTitledBorder("GET TESTS"));


        JPanel getTestOptions = new JPanel();
        getTestOptions.setLayout(new BoxLayout(getTestOptions, BoxLayout.Y_AXIS));

        JTextField sampleId = addLabelAt(getTestOptions, "Sample id", "123456789");
        JTextField rackId = addLabelAt(getTestOptions, "Rack id", "R0001");
        JTextField holeId = addLabelAt(getTestOptions, "Hole id", "A12");

        bGet = new JButton("Push [get tests] request");
        getTests.add(inFlow(bGet), BorderLayout.SOUTH);
        getTests.add(getTestOptions, BorderLayout.CENTER);

        JPanel sendResults = new JPanel(new BorderLayout());
        sendResults.setBorder(BorderFactory.createTitledBorder("SEND RESULTS"));


        bSend = new JButton("Push [send results] request");
        sendResults.add(inFlow(bSend), BorderLayout.SOUTH);


        JPanel sendResultsOptions = new JPanel();
        sendResultsOptions.setLayout(new BoxLayout(sendResultsOptions, BoxLayout.Y_AXIS));
        outputRackId = addLabelAt(sendResultsOptions, "Rack id", "R0001");
        outputHoleId = addLabelAt(sendResultsOptions, "Hole id", "A12");
        sendResults.add(sendResultsOptions, BorderLayout.CENTER);



        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(server);
        main.add(getTests);
        main.add(sendResults);

        topPanel.add(main, BorderLayout.CENTER);

        // Create the split pane and add the top panel and the text area
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, scrollPane);
        splitPane.setDividerLocation(500); // Initial position of the divider
        splitPane.setResizeWeight(0.5); // Distribute space equally initially

        // Add the split pane to the frame
        frame.add(splitPane);

        // Make the frame visible
        frame.setVisible(true);

        // Example output to the console


        bSend.setEnabled(false);
        bGet.setEnabled(false);

        bStop.setEnabled(false);
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bStop.setEnabled(true);
                bStart.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        log.info("Service started");
                        atta = new AstmThread(true, "0.0.0.0", Integer.parseInt(port.getText()));
                        atta.start();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                bSend.setEnabled(true);
                                bGet.setEnabled(true);
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
                bSend.setEnabled(false);
                bGet.setEnabled(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        atta.kill();
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



        bGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ATTA_Simulator.getTests(atta, sampleId.getText(),rackId.getText(),
                            holeId.getText());
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }


            }
        });


        bSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ATTA_Simulator.sendResults(atta, sampleId.getText(),
                            outputRackId.getText(), outputHoleId.getText());
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }

            }
        });



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
        panel.add(myLabel,BorderLayout.WEST);
        panel.add(myTextField,BorderLayout.CENTER);


        getTestOptions.add(panel);
        return myTextField;
    }

    private static JPanel inFlow(JButton b) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(b);
        return p;
    }
}