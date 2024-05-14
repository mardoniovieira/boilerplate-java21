package br.com.boilerplate.java21.infrastructure.configuration.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile({"!prod"})
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title("Boilerplate")
                        .description("Boilerplate Java 21")
                        .version("1.0.0"));

        openAPI.addServersItem(new Server().url("http://localhost:8080"));
        // openAPI.addServersItem(new Server().url("https://my-dev-website.com.br/api"));
        // openAPI.addServersItem(new Server().url("https://my-qa-website.com.br/api"));
        // openAPI.addServersItem(new Server().url("https://my-hom-website.com.br/api"));

        return openAPI;
    }
}