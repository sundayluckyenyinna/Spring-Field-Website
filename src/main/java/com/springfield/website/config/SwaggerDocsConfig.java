package com.springfield.website.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SwaggerDocsConfig {


    @Bean
    public OpenAPI omnixRootOpenAPI() {
        // Define API Info
        Info apiInfo = new Info()
                .title("SpringField Website")
                .version("1");

        // Define Server
        Server server = new Server()
                .url("/")
                .description("SpringField website");

        // Build the OpenAPI object
        OpenAPI openAPI = new  OpenAPI()
                .info(apiInfo)
                .addServersItem(server);
        return openAPI;
    }
}
