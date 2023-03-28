package com.youhogeon.kakaobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServerRunner {

    private final DatagramSocket socket;
    private final ReceiveController receiveController;

    private final int BUFFER_SIZE = 32767;

    public void run() {
		while (true) {
            receive();
        }
    }

    public void receive() {
        DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

        try{
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();

            return;
        }

        Thread thread = new Thread(() -> {
            receiveController.receive(packet);
        });

        thread.start();
    }

    public void close() {
        socket.close();
    }

}
