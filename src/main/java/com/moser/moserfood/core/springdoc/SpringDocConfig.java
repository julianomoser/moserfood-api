package com.moser.moserfood.core.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Juliano Moser
 */
@Configuration
@SecurityScheme(name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                scopes = {
                        @OAuthScope(name = "READ", description = "read scope"),
                        @OAuthScope(name = "WRITE", description = "write scope")
                }
        )))
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MoserFood API")
                        .version("v1")
                        .description("REST API do MoserFood")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.com")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("MoserFood")
                        .url("https://api.moserfood.com.br"))
                .tags(Arrays.asList(
                        new Tag().name("Cidades").description("Gerencia as cidades"),
                        new Tag().name("Grupos").description("Gerencia os grupos"),
                        new Tag().name("Cozinhas").description("Gerencia as cozinhas"),
                        new Tag().name("Formas de pagamento").description("Gerencia as formas de pagamento"),
                        new Tag().name("Pedidos").description("Gerencia os pedidos"),
                        new Tag().name("Restaurantes").description("Gerencia os restaurantes"),
                        new Tag().name("Estados").description("Gerencia os estados"),
                        new Tag().name("Produtos").description("Gerencia os produtos"),
                        new Tag().name("Usuários").description("Gerencia os usuários"),
                        new Tag().name("Permissões").description("Gerencia as permissões"),
                        new Tag().name("Estatísticas").description("Estatísticas da MoserFood")
                ));
    }
}
