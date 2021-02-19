package com.luv2code.demo.demo.configs;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    @Value("${springdoc.swagger-ui.path:#{null}}")
    private String springdocSwaggerUiPath;

    @Bean
    public OpenAPI reviewApiOpenApi()
    {
        final Info info = new Info() //
                .title("Spring Security Swagger") //
                .description("test Swagger") //
                .version("v2");

        return new OpenAPI() //
                .components(new Components().addSecuritySchemes(
                        "BEARER_TOKEN",
                        new SecurityScheme()
                                .description("Please enter your SSO token. For SSO token, add the \"sso-\" prefix.")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"))) //
                .info(info);
    }


//    @Bean
//    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser()
//    {
//        final Map<String, ApiResponse> defaultErrorResponses = Stream.of(HttpStatus.BAD_REQUEST,
//                HttpStatus.UNAUTHORIZED,
//                HttpStatus.FORBIDDEN,
//                HttpStatus.UNPROCESSABLE_ENTITY,
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                HttpStatus.BAD_GATEWAY) //
//                .collect(Collectors.toMap(
//                        // key
//                        e -> String.valueOf(e.value()),
//                        // value
//                        e -> new ApiResponse().description(e.getReasonPhrase())
//                                .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
//                                        new MediaType().schema(new Schema().$ref("#/components/schemas/ErrorData"))))
//                        //
//                ));
//
//        return openApi -> {
//            openApi.getPaths()
//                    .values()
//                    .stream()
//                    .flatMap(path -> path.readOperations().stream())
//                    .map(Operation::getResponses);
//        };
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        if (StringUtils.isNotEmpty(springdocSwaggerUiPath))
        {
            registry.addRedirectViewController("/docs", springdocSwaggerUiPath);
            registry.addRedirectViewController("/docs/", springdocSwaggerUiPath);
        }
    }
}
