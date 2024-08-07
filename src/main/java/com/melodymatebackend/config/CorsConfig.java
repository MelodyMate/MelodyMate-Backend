package com.melodymatebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP method
            .allowedOrigins("http://localhost:3000", "https://melodymate.netlify.app")
            .allowedHeaders("*")
            .allowCredentials(true)
            .exposedHeaders("Set-Cookie");
    }
}
