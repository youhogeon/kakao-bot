package com.youhogeon.kakaobot.service;

import com.youhogeon.kakaobot.dto.KakaoDto;

public interface ServiceAdapter {
    
    public String process(KakaoDto message);
    
    public boolean isSupported(KakaoDto message);

}
