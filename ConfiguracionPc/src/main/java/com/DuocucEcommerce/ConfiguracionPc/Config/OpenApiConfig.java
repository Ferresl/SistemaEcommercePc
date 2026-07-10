package com.DuocucEcommerce.ConfiguracionPc.Config;

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
                        .title("Microservicio de Configuracion PC")
                        .version("1.0.0")
                        .description("Documentacion de endpoints del microservicio de configuraciones de PC"));
    }
}
