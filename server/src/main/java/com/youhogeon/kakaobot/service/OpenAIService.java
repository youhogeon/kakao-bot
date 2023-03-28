package com.youhogeon.kakaobot.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youhogeon.kakaobot.dto.OpenAIRequest;
import com.youhogeon.kakaobot.dto.OpenAIResponse;
import com.youhogeon.kakaobot.dto.KakaoDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenAIService implements Service {

    private final ObjectMapper objectMapper;

    @Value("${openai.secretkey}")
    private String secretKey;

    @Value("${openai.systemDefinition}")
    private String systemDefinition;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.maxTokens}")
    private int maxTokens;

    @Value("${openai.timeout}")
    private int timeout = 300000;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final String PREFIX = "얌마 ";

    private String removePrefix(String message) {
        return message.substring(PREFIX.length());
    }

    @Override
    public String process(KakaoDto message) {
        String requestMessage = removePrefix(message.getContent());
        OpenAIRequest chatGPTRequest = new OpenAIRequest();
        chatGPTRequest.setModel(model);
        chatGPTRequest.setMaxTokens(maxTokens);
        chatGPTRequest.addMessage("system", systemDefinition);
        chatGPTRequest.addMessage("user", requestMessage);


        try {
            System.out.println(objectMapper.writeValueAsString(chatGPTRequest));
            String result = Jsoup.connect(OPENAI_API_URL)
                .header("Authorization", "Bearer " + secretKey)
                .header("Content-Type", "application/json")
                .requestBody(objectMapper.writeValueAsString(chatGPTRequest))
                .method(Method.POST)
                .ignoreContentType(true)
                .timeout(timeout)
                .execute()
                .body();

            OpenAIResponse gptDto = objectMapper.readValue(result, OpenAIResponse.class);

            return gptDto.getChoices().get(0).getMessage().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "얌마에게 문제가 생겼어요! 😯";
    }

    @Override
    public boolean isSupported(KakaoDto message) {
        if (secretKey == null || secretKey.length() == 0) return false;

        if (message.getContent().startsWith(PREFIX)) return true;

        return false;
    }

    @Override
    public String getDescription() {
        return "얌마 {메시지} 👉 얌마와 대화를 나눌 수 있어요! (ChatGPT 3.5)";
    }
    
}
