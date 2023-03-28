package com.youhogeon.kakaogpt.service;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaogpt.dto.KakaoDto;

@Component
public class TestServiceAdaptor implements ServiceAdaptor {

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
