package com.youhogeon.kakaogpt.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Sender {

    private final DatagramSocket socket;

    public void send(DatagramPacket receivedPacket, String message) {
        byte[] buffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, receivedPacket.getAddress(), receivedPacket.getPort());

        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            return;
        }

    }
    
}
