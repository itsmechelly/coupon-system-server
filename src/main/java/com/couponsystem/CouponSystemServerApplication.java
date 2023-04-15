package com.couponsystem;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.couponsystem.filters.TokenFilter;
import com.couponsystem.security.JwtUtil;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class CouponSystemServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponSystemServerApplication.class, args);
        System.out.println("spring is ready!");
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilterRegistration(JwtUtil jwtUtil) {
        FilterRegistrationBean<TokenFilter> filterRegistrationBean = new FilterRegistrationBean<TokenFilter>();
        TokenFilter tokenFilter = new TokenFilter(jwtUtil);
        filterRegistrationBean.setFilter(tokenFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://coupon-express.s3-website-us-east-1.amazonaws.com"));
        configuration.setAllowedOrigins(List.of("http://44.204.1.11:8080"));
        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "HEAD",
                "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
                "Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Origin",
                "Cache-Control",
                "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
