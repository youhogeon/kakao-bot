package com.youhogeon.kakaogpt.server;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youhogeon.kakaogpt.dto.KakaoDto;
import com.youhogeon.kakaogpt.service.ServiceAdaptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReceiveController {

    private final Sender sender;
    private final ObjectMapper objectMapper;
    private final List<ServiceAdaptor> serviceAdaptors;

    public void receive(DatagramPacket packet) {
        KakaoDto kakaoDto;

        try {
            byte[] character = packet.getData();
            String data = new String(character, "UTF-8");
        
            kakaoDto = objectMapper.readValue(data, KakaoDto.class);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        for (ServiceAdaptor serviceAdaptor : serviceAdaptors) {
            if (!serviceAdaptor.isSupported(kakaoDto)) continue;

            String result = serviceAdaptor.process(kakaoDto);
            sender.send(packet, result);
        }
    }
    
}
