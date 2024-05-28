package com.example.documenttest.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        servers = {@Server(url = "/sm", description = "Default Server URL")},
        info = @Info(
                title = "DocumentTest",
                version = "1.0",
                description = "DocumentTest"
        )
)
@Configuration
public class OpenApiConfig {
}
