package com.wealthmap.wealthmap_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.ExternalDocumentation;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI wealthMapOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Wealth-Map API")
                        .description("Property and Wealth Management Platform")
                        .version("v1.0")
                        .contact(new Contact().name("API Support").email("support@wealthmap.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Wealth-Map Documentation"));
    }
}