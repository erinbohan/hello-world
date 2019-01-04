package com.frankc.hellomongo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.frankc.hellomongo.controllers.ApiKeyController;
import com.frankc.hellomongo.controllers.ShortUrlController;
import com.frankc.hellomongo.security.ShortUrlSecurityConfig;
import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${api.key.header.name:"
           + ShortUrlSecurityConfig.DEFAULT_API_KEY_HEADER + "}")
    private String apiKeyHeader;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .ignoredParameterTypes(Authentication.class)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(paths())
          .build()
          .securitySchemes(Arrays.asList(apiKey()))
          .securityContexts(Arrays.asList(securityContext()))
          .useDefaultResponseMessages(false);
    }

    private Predicate<String> paths() {
        return PathSelectors.regex("^(" + ShortUrlController.BASE_PATH + "|"
                                   + ApiKeyController.BASE_PATH + ").*$");
    }

    private ApiKey apiKey() {
        return new ApiKey(apiKeyHeader, apiKeyHeader, "header");
      }

      private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(paths())
            .build();
      }

      List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
            new SecurityReference(apiKeyHeader, authorizationScopes));
      }

      @Bean
      public SecurityConfiguration security() {
              return SecurityConfigurationBuilder.builder()
                      .clientId("test-app-client-id")
                      .clientSecret("test-app-client-secret")
                      .realm("test-app-realm")
                      .appName("test-app")
                      .scopeSeparator(",")
                      .additionalQueryStringParams(null)
                      .useBasicAuthenticationWithAccessCodeGrant(false)
                      .build();
      }
}
