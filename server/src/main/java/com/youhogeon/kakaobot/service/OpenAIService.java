package com.youhogeon.kakaobot.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youhogeon.kakaobot.dto.OpenAIReq;
import com.youhogeon.kakaobot.dto.OpenAIRes;
import com.youhogeon.kakaobot.dto.KakaoReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
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
    private final String DESCRIPTION = "얌마 {메시지} 👉 얌마와 대화를 나눌 수 있어요! (ChatGPT 3.5)";
    private final String ERROR_MSG = "얌마에게 문제가 생겼어요! 😯";

    private String removePrefix(String message) {
        return message.substring(PREFIX.length());
    }

    private String requestAPI(OpenAIReq chatGPTRequest) {
        try {
            String result = Jsoup.connect(OPENAI_API_URL)
                .header("Authorization", "Bearer " + secretKey)
                .header("Content-Type", "application/json")
                .requestBody(objectMapper.writeValueAsString(chatGPTRequest))
                .method(Method.POST)
                .ignoreContentType(true)
                .timeout(timeout)
                .execute()
                .body();
            
            OpenAIRes gptDto = objectMapper.readValue(result, OpenAIRes.class);

            return gptDto.getChoices().get(0).getContent();
    
        } catch (IOException e) {
            log.warn("OpenAI API 호출 실패", e, chatGPTRequest);
        }

        return ERROR_MSG;
    }

    @Override
    public String process(KakaoReq message) {
        String requestMessage = removePrefix(message.getContent());
        OpenAIReq chatGPTRequest = new OpenAIReq();
        chatGPTRequest.setModel(model);
        chatGPTRequest.setMaxTokens(maxTokens);
        chatGPTRequest.addMessage("system", systemDefinition);
        chatGPTRequest.addMessage("user", requestMessage);

        return requestAPI(chatGPTRequest);
    }

    @Override
    public boolean isSupported(KakaoReq message) {
        if (secretKey == null || secretKey.length() == 0) return false;

        if (message.getContent().startsWith(PREFIX)) return true;

        return false;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
    
}
