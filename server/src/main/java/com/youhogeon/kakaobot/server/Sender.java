package com.youhogeon.kakaobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final DatagramSocket socket;

    public void send(DatagramPacket receivedPacket, String message) {
        byte[] buffer = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, receivedPacket.getAddress(), receivedPacket.getPort());

        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            log.warn("소켓 송신 실패", e, message);

            return;
        }

    }
    
}
