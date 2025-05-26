package com.wealthmap.wealthmap_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")             // Allow all endpoints
                .allowedOrigins("http://127.0.0.1:5500/index.html")  // React dev server URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // HTTP methods you use
                .allowedHeaders("*")             // Allow all headers
                .allowCredentials(true);         // Allow cookies, auth headers, etc.
    }
}
