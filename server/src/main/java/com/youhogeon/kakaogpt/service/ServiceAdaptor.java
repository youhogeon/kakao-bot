package com.youhogeon.kakaogpt.service;

import com.youhogeon.kakaogpt.dto.KakaoDto;

public interface ServiceAdaptor {
    
    public String process(KakaoDto message);
    
    public boolean isSupported(KakaoDto message);

}
