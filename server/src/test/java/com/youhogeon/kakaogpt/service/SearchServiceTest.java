package com.youhogeon.kakaogpt.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.youhogeon.kakaobot.dto.KakaoDto;
import com.youhogeon.kakaobot.service.SearchService;

@SpringJUnitConfig(classes = {SearchService.class})
public class SearchServiceTest {
    
    @Autowired
    SearchService searchService;

    @Test
    void 지원_테스트() {
        // given
        KakaoDto kakaoDto = KakaoDto.builder()
            .content("구글 검색")
            .build();

        // when
        boolean result = searchService.isSupported(kakaoDto);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 지원_응답_테스트() {
        // given
        String encoded = "%EA%B2%80%EC%83%89";
        String decoded = "구글 검색";

        KakaoDto kakaoDto = KakaoDto.builder()
            .content(decoded)
            .build();

        // when
        String result = searchService.process(kakaoDto);

        // then
        assertThat(result)
            .contains("google.com")
            .contains(encoded)
            .doesNotContain(decoded);
    }

    @Test
    void 미지원_테스트() {
        // given
        KakaoDto kakaoDto = KakaoDto.builder()
            .content("구글검색")
            .build();

        // when
        boolean result = searchService.isSupported(kakaoDto);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 미지원_테스트_긴_내용() {
        // given
        String content = "구글 십계명 3번 : Google은 시간의 소중함을 알기에 웹에서 정보를 검색할 때 원하는 결과를 곧바로 제공해 드리기 위해 최선을 다하고 있습니다. 사용자가 최대한 빨리 웹사이트를 떠나도록 하는 것이 목표라고 하는 기업은 아마 Google이 유일할 것입니다. Google은 페이지에서 불필요한 콘텐츠를 전부 없애고 서비스 환경의 효율성을 높임으로써 검색 속도 기록을 자체적으로 계속 갱신하고 있습니다. 그 결과 검색결과의 평균 응답 시간이 1초도 걸리지 않습니다. Google은 모바일 애플리케이션이든 Chrome이든 새로운 제품을 출시할 때마다 속도를 항상 염두에 둡니다. Google은 지금도 더 빠른 서비스를 제공하기 위해 끊임없이 노력하고 있습니다.";

        KakaoDto kakaoDto = KakaoDto.builder()
            .content(content)
            .build();

        // when
        boolean result = searchService.isSupported(kakaoDto);

        // then
        assertThat(result).isFalse();
    }

}
