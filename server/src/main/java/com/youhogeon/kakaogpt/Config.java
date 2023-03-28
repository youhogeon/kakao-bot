package com.youhogeon.kakaogpt;

import java.net.DatagramSocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Config {
    
    @Bean
    public DatagramSocket datagramSocket(
        @Value("${server.port}") int port
    ) throws Exception {
        return new DatagramSocket(port);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
