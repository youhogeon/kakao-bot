package com.youhogeon.kakaogpt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.youhogeon.kakaobot.dto.KakaoReq;
import com.youhogeon.kakaobot.service.TestService;

@SpringJUnitConfig(classes = {TestService.class})
public class TestServiceTest {
    
    @Autowired
    TestService testService;

    @Test
    void 지원_테스트() {
        // given
        KakaoReq kakaoDto = KakaoReq.builder()
            .content("테스트")
            .build();

        // when
        boolean result = testService.isSupported(kakaoDto);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 미지원_테스트() {
        // given
        KakaoReq kakaoDto = KakaoReq.builder()
            .content("테스트아님")
            .build();

        // when
        boolean result = testService.isSupported(kakaoDto);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 작동_테스트() {
        // given
        String name = UUID.randomUUID().toString();
    
        KakaoReq kakaoDto = KakaoReq.builder()
            .content("테스트")
            .name(name)
            .build();

        // when
        String result = testService.process(kakaoDto);

        // then
        assertThat(result)
            .isNotNull()
            .isNotEmpty()
            .isNotBlank()
            .hasSizeGreaterThan(10)
            .contains(name);
    }

}
