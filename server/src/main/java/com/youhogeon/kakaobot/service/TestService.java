package com.youhogeon.kakaobot.service;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaobot.dto.KakaoReq;

@Component
public class TestService implements Service {

    @Override
    public String process(KakaoReq message) {
        return "테스트 성공입니다! " + message.getName() + "님";
    }

    @Override
    public boolean isSupported(KakaoReq message) {
        if (message.getContent().equals("테스트")) return true;

        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
    
}
