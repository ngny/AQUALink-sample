package com.ngnydevices.open.a9000p;

public interface  PayloadListener {
        void onReceivedPayload(TcpStateMachine tcp, Payload q);
}
