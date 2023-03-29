package com.youhogeon.kakaobot.service;

import com.youhogeon.kakaobot.dto.KakaoReq;

public interface Service {
    
    public String process(KakaoReq message);
    
    public boolean isSupported(KakaoReq message);

    public String getDescription();

}
