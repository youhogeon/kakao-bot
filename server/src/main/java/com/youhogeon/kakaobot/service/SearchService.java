package com.youhogeon.kakaobot.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.youhogeon.kakaobot.dto.KakaoDto;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SearchService implements Service {

    private final Map<String, String> map = new TreeMap<String, String>();
    private final int MAX_QUERY_LENGTH = 100;
    private String description;

    @PostConstruct
    public void init() {
        map.put("구글", "https://www.google.com/search?q=");
        map.put("네이버", "https://search.naver.com/search.naver?query=");
        map.put("다음", "https://search.daum.net/search?q=");
        map.put("지도", "https://map.kakao.com/?q=");
    }

    @Override
    public String process(KakaoDto message) {
        String[] split = split(message.getContent());
        String query = "";

        try {
            query = URLEncoder.encode(split[1], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("검색어 문자열 인코딩 실패", e, message);
        }

        return map.get(split[0]) + query;
    }

    @Override
    public boolean isSupported(KakaoDto message) {
        String[] split = split(message.getContent());

        if (map.containsKey(split[0]) && split[1].length() <= MAX_QUERY_LENGTH) return true;

        return false;
    }

    private String[] split(String content) {
        String[] result = new String[2];
        String[] split = content.split(" ");

        result[0] = split[0];
        result[1] = split.length > 1 ? content.substring(split[0].length() + 1) : "";

        return result;
    }

    @Override
    public String getDescription() {
        if (description != null) return description;

        String supported = map.keySet()
            .stream()
            .collect(Collectors.joining(", "));

        description = "{검색엔진명} {검색어} 👉 검색 링크를 만들어줘요! (" + supported + ")";

        return description;
    }
    
}
