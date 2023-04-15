package com.couponsystem.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couponsystem.security.CustomSession;

@Configuration
public class HashMapConfig {
    @Bean
    public HashMap<String, CustomSession> tokensInMemory() {
        return new HashMap<>();
    }
}
