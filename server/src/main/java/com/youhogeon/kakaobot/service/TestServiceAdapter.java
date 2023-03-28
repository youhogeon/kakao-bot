package com.youhogeon.kakaobot.service;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaobot.dto.KakaoDto;

@Component
public class TestServiceAdapter implements ServiceAdapter {

    @Override
    public String process(KakaoDto message) {
        return "테스트 성공입니다! " + message.getAuther().getName() + "님";
    }

    @Override
    public boolean isSupported(KakaoDto message) {
        if (message.getContent().equals("테스트")) return true;

        return false;
    }
    
}
