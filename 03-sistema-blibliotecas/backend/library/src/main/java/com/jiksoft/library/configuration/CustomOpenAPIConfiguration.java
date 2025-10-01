package com.jiksoft.library.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomOpenAPIConfiguration {

    @Value("${swagger.server.url}")
    private String serverUrl;

    @Value("${project.name}")
    private String projectName;

    @Value("${project.version}")
    private String projectVersion;

    @Bean
    public OpenAPI customOpenAPI() {

        var server = new Server();
        server.setDescription("Server URL");
        server.setUrl(serverUrl);

        return new OpenAPI()
            .servers(Collections.singletonList(server))
            .info(new Info()
                .title(projectName)
                .version(projectVersion));
    }
}
