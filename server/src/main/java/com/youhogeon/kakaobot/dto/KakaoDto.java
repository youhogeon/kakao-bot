package com.youhogeon.kakaobot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class KakaoDto {
    
    private String content;
    private String room;
    private Auther auther;

    @JsonProperty("isGroupChat")
    private boolean isGroupChat;

    @JsonProperty("isDebugRoom")
    private boolean isDebugRoom;

}
