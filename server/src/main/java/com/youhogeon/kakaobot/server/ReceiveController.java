package com.youhogeon.kakaobot.server;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youhogeon.kakaobot.dto.KakaoReq;
import com.youhogeon.kakaobot.service.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReceiveController {

    private final Sender sender;
    private final ObjectMapper objectMapper;
    private final List<Service> services;

    public void receive(DatagramPacket packet) {
        KakaoReq kakaoDto;
        byte[] character = null;

        try {
            character = packet.getData();
            String data = new String(character, "UTF-8");
        
            kakaoDto = objectMapper.readValue(data, KakaoReq.class);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            log.warn("수신된 JSON 파싱 실패", e, character);
            e.printStackTrace();
            return;
        }

        process(kakaoDto, packet);
    }

    private void process(KakaoReq kakaoDto, DatagramPacket packet) {
        for (Service service : services) {
            if (!service.isSupported(kakaoDto)) continue;

            String result = service.process(kakaoDto);
            sender.send(packet, result);
        }
    }
    
}
