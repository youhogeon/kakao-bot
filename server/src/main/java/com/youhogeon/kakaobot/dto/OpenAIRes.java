package com.youhogeon.kakaobot.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class OpenAIRes {
    
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;
    
    @Getter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;
        
    }
    
    @Getter
    public static class Choice {
        private int index;

        @JsonProperty("finish_reason")
        private String finishReason;

        private String role;
        private String content;
        
        @JsonProperty("message")
        private void unpackNameFromNestedObject(Map<String, String> auther) {
            role = auther.get("role");
            content = auther.get("content");
        }
    }

}
