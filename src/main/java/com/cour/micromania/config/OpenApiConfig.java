package com.cour.micromania.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Micromania API",
                version = "1.0",
                description = "Gestion des produits (accessoires, consoles, jeux video), des clients, des employes et des magasins."
        ),
        servers = @Server(url = "/", description = "Serveur local")
)
public class OpenApiConfig {
}
