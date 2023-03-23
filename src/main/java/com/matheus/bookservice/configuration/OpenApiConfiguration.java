package com.matheus.bookservice.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(info =
@Info(title = "Book Service API", version = "1", description = "Book service's docs"))
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Book Service API").version("1")
                        .license(new License().name("Apache").url("teste.com")));
    }
}
