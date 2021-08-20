package br.com.alura.aluraflix.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {
    @Bean
    public OpenAPI myOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI().info(new Info().title("Aluraflix")
            .description("É uma API para uma Plataforma de compatilhamento de vídeos. A plataforma deve permitir ao usuário montar playlists com links para seus vídeos preferidos, separados por categorias.")
            .version("v1"))
            .components(
                new Components().addSecuritySchemes(securitySchemeName,
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))
            );
    }
}
