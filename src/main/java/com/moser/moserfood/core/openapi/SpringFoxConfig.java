package com.moser.moserfood.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moser.moserfood.api.exceptionhandler.Problem;
import com.moser.moserfood.api.model.CozinhaDTO;
import com.moser.moserfood.api.model.PedidoResumoDTO;
import com.moser.moserfood.api.openapi.model.PageableDTOOpenApi;
import com.moser.moserfood.api.openapi.model.PagedModelOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Juliano Moser
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moser.moserfood.api"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/restaurantes/*"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
//                .globalRequestParameters(Collections.singletonList(
//                        new RequestParameterBuilder()
//                                .name("campos")
//                                .description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
//                                .in(ParameterType.QUERY)
//                                .required(true)
//                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                                .build()
//                ))
                .additionalModels(typeResolver.resolve(Problem.class))
                .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class, Resource.class,
                        File.class, InputStream.class)
                .directModelSubstitute(Pageable.class, PageableDTOOpenApi.class)
                .alternateTypeRules(buildPageTypeRole(typeResolver, CozinhaDTO.class))
                .alternateTypeRules(buildPageTypeRole(typeResolver, PedidoResumoDTO.class))
                .apiInfo(apiInfo())
                .tags(tags()[0], tags());
    }

    private static <T> AlternateTypeRule buildPageTypeRole(TypeResolver typeResolver, Class<T> classModel) {
        return AlternateTypeRules.newRule(
                typeResolver.resolve(Page.class, classModel),
                typeResolver.resolve(PagedModelOpenApi.class, classModel));
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MoserFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(new Contact(
                        "MoserFood", "https://www.moserfood.com.br", "moserfood@gmail.com"))
                .build();
    }

    private Consumer<RepresentationBuilder> getProblemaModelReference() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problema").namespace("com.moser.moserfood.api.exceptionhandler")))));
    }

    private Tag[] tags() {
        return new Tag[]{
                new Tag("City", "Manage the cities"),
                new Tag("Cuisine", "Manage the cuisines"),
                new Tag("Group", "Manage the groups"),
                new Tag("Group permission", "Manage the groups permissions"),
                new Tag("Order", "Manage the orders"),
                new Tag("Order status", "Manage the orders status"),
                new Tag("Payment method", "Manage the payment methods"),
                new Tag("Product photo", "Manage the products photos"),
                new Tag("Restaurant", "Manage the restaurants"),
                new Tag("Restaurant payment method", "Manage the restaurants payment methods"),
                new Tag("Restaurant product", "Manage the restaurants products"),
                new Tag("Restaurant user", "Manage the restaurants owners"),
                new Tag("State", "Manage the states"),
                new Tag("Statistic", "Manage the statistics"),
                new Tag("User", "Manage the users"),
                new Tag("User group", "Manage the users groups")
        };
    }
}
