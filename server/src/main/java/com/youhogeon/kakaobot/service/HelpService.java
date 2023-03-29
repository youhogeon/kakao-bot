package com.youhogeon.kakaobot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaobot.dto.KakaoDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelpService implements Service {

    private final List<Service> services;

    private final String COMMAND = "얌마";
    private String descriptions;

    @PostConstruct
    public void init() {
        List<String> descriptions = new ArrayList<>();

        for (Service service : services) {
            String description = service.getDescription();
            if (description == null) continue;

            descriptions.add(description);
        }

        this.descriptions = descriptions.stream().collect(Collectors.joining("\n\n"));
    }

    @Override
    public String process(KakaoDto message) {
        return descriptions;
    }

    @Override
    public boolean isSupported(KakaoDto message) {
        if (message.getContent().equals(COMMAND)) return true;

        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }

}