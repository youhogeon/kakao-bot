package com.youhogeon.kakaobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class KakaoDto {
    
    public KakaoDto() {}

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
    @Builder
    @AllArgsConstructor
    public static class Auther {

        public Auther() {}
        
        private String name;
        private String avatar;
    
    }

}
