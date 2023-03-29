package com.youhogeon.kakaobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoDto {
    
    private String content;
    private String room;
    private Auther auther;

    @JsonProperty("isGroupChat")
    private boolean isGroupChat;

    @JsonProperty("isDebugRoom")
    private boolean isDebugRoom;

    @Getter
    @EqualsAndHashCode
    @ToString
    public static class Auther {
        
        private String name;
        private String avatar;
    
    }

}
