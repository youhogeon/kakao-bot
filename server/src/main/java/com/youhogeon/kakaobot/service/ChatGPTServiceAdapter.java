package com.youhogeon.kakaobot.service;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaobot.dto.KakaoDto;

@Component
public class ChatGPTServiceAdapter implements ServiceAdapter {

    @Override
    public String process(KakaoDto message) {
        // TODO  : CHAT GPT 서버에 요청 후 응답을 받음

        return "CHAT GPT 응답 구현 예정입니다. " + message.getAuther().getName() + "님";
    }

    @Override
    public boolean isSupported(KakaoDto message) {
        if (message.getContent().startsWith("얌마 ")) return true;

        return false;
    }
    
}
