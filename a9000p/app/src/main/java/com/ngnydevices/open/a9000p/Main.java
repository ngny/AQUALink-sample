
package com.ngnydevices.open.a9000p;


import com.ngnydevices.open.a9000p.errors.DataTransmissionException;
import com.ngnydevices.open.a9000p.errors.TimeoutException;
import com.ngnydevices.open.a9000p.sims.ATTA_Simulator;
import com.ngnydevices.open.a9000p.sims.LIS_Simulator;

class Main {


    private final static BasicLogger log = new BasicLogger(Main.class.getName());

    static final boolean LAUNCH_CLIENT = true;
    static final boolean LAUNCH_SERVER = true;

    public static void main(String[] argv) throws Exception, TimeoutException, DataTransmissionException {

        for(String s :argv) {
            if ("--lis-simulator".equals(s)) {
                LisApp.main(argv);
                return;
            }
            if ("--instrument-simulator".equals(s)) {
                InstrumentApp.main(argv);
                return;
            }
        }

        log.info("IMPORTANT: Only bytes read/written at LIS Simulator socket are displayed in this log file.");


        final int port = 55551;

        AstmThread atta = null;
        AstmThread lis = null;

        if (LAUNCH_CLIENT) {
            atta = new AstmThread(true, "127.0.0.1", port);
            atta.start();
            TimeHelper.sleep(200);
        }


        if (LAUNCH_SERVER) {
            lis = new AstmThread(false, "127.0.0.1", port);
            lis.setListener((tcp, q) -> LIS_Simulator.onReceivedPayload(tcp,q));
            lis.start();
        }

        if (LAUNCH_CLIENT) {

            while (atta.getStatus() != AstmThread.Status.StartedReady) {
                TimeHelper.sleep(10);
            }


            log.info("Waiting...");

            ATTA_Simulator.getTests(atta, "12345","RACK123", "A1");

            ATTA_Simulator.sendResults(atta, "12345",
                    "OUTPUT1","B1");

        }







        if (atta!=null) {
            atta.join();
        }
        if (lis!=null) {
            lis.join();
        }


    }



}
