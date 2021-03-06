
package tech.ngnydevices.aqualis._3_0;


import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2018-06-06T18:23:53.990+02:00
 * Generated source version: 3.2.4
 */

public class Main {

    protected Main() throws Exception {
    }

    public static void changeEndpoint(BindingProvider bp) {
        String original = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        String endpointURL = original.replaceAll("http://LIS1:4567", "http://localhost:4567");
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
    }


    public static EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(123L)
            .randomizationDepth(5)
            .stringLengthRange(5, 5)
            .collectionSizeRange(1, 2)
            .build();


    public static void main(String args[]) throws Exception {


        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");


        switch (args[0]) {
            case "server":
                int httpPort = 4567;

                Endpoint.publish("http://localhost:" + httpPort + "/aqualis/HomingPort", new HomingPortImpl());
                Endpoint.publish("http://localhost:" + httpPort + "/aqualis/TestPort", new TestServicePortImpl());
                Endpoint.publish("http://localhost:" + httpPort + "/aqualis/ResultPort", new ResultServicePortImpl());
                Endpoint.publish("http://localhost:" + httpPort + "/aqualis/TestBulkUploadPort", new TestBulkUploadPortImpl());
                System.out.println("Server started at port " + httpPort + ". Ctrl-C to stop the server.");
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(1000);
                }

                break;


            case "client":
                TestService ss = new TestService(TestService.WSDL_LOCATION, new QName("http://www.ngnydevices.tech/aqualis/3-0", "TestService"));
                switch (args[1]) {
                    case "homing": {
                        HomingPortType port = ss.getHomingPort();
                        changeEndpoint((BindingProvider) port);
                        port.conveyorInitialization(random.nextObject(ConveyorInitialization.class));
                        break;
                    }

                    case "get-tests":
                    {
                        TestServicePortType port = ss.getTestServicePort();
                        changeEndpoint((BindingProvider) port);
                        port.getTests(random.nextObject(GetTests.class));
                        break;
                    }

                    case "send-results": {
                        ResultServicePortType port = ss.getResultServicePort();
                        changeEndpoint((BindingProvider) port);
                        port.sendResults(random.nextObject(SendResults.class));
                        break;
                    }

                    case "bulk": {
                        TestBulkUploadPortType port = ss.getTestBulkUploadPort();
                        changeEndpoint((BindingProvider) port);
                        port.uploadTests(random.nextObject(BulkOrder.class));
                        break;
                    }
                }


        }


        /// return  Main.random.nextObject(BulkOrderResponse.class);


        System.exit(0);
    }
}
