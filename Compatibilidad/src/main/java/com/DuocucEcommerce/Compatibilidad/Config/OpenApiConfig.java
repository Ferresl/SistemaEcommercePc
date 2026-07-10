package com.DuocucEcommerce.Compatibilidad.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Compatibilidad")
                        .version("1.0.0")
                        .description("Documentacion de endpoints del microservicio de compatibilidad de componentes"));
    }
}
