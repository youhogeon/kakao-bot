package com.youhogeon.kakaobot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenAIReq {
    
    private String model = "gpt-3.5-turbo";

    private List<Message> messages = new ArrayList<>();

    @JsonProperty("max_tokens")
    private int maxTokens = 1024;

    public void addMessage(String role, String content) {
        messages.add(new Message(role, content));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Message {

        private String role = "user";

        private String content;

    }
}
