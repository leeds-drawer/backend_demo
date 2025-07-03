package com.community.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition(
  info = @io.swagger.v3.oas.annotations.info.Info(
    title       = "Community API",
    version     = "1.0",
    description = "Community Backend API Documentation"
  ),
  security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
  name         = "bearerAuth",
  type         = SecuritySchemeType.HTTP,
  scheme       = "bearer",
  bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Community API")
                .version("1.0")
                .description("Community Backend API Documentation")
                .contact(new Contact()
                    .name("Developer")
                    .email("dev@example.com")
                )
            );
    }
}
