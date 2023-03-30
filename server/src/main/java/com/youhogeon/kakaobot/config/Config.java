package com.youhogeon.kakaobot.config;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Config {

    @Bean
    public DatagramSocket datagramSocket(
        @Value("${server.port}") int port
    ) {
        try {
            return new DatagramSocket(port);
        } catch (SocketException e) {
            log.error("포트 바인딩 실패", e);
        }

        return null;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    // @Bean
    // JedisConnectionFactory jedisConnectionFactory() {
    //     RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);

    //     return new JedisConnectionFactory(config);
    // }
    
    // @Bean
    // public RedisTemplate<String, Object> redisTemplate() {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory());

    //     return template;
    // }

}
