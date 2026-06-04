package com.microservicio1.microservicio1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebClientConfing {
    
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
 