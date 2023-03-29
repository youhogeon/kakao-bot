package com.youhogeon.kakaobot.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoReq {

    private String content;
    private String room;

    private String name;
    private String avatar;

    @JsonProperty("auther")
    private void unpackNameFromNestedObject(Map<String, String> auther) {
        name = auther.get("name");
        avatar = auther.get("avatar");
    }

    @JsonProperty("isGroupChat")
    private boolean isGroupChat;

    @JsonProperty("isDebugRoom")
    private boolean isDebugRoom;

}
