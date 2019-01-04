package com.frankc.hellomongo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.repositories.ApiKeyRepoJpa;
import com.frankc.hellomongo.controllers.ShortUrlController;

@Configuration
@EnableWebSecurity
@Order(1)
public class ShortUrlSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String DEFAULT_API_KEY_HEADER = "API-KEY";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiKeyRepoJpa apiKeyRepo;

    @Value("${api.key.header.name:" + DEFAULT_API_KEY_HEADER + "}")
    private String principalRequestHeader;

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        APIKeyAuthFilter apiKeyFoundFilter =
                    new APIKeyAuthFilter(apiKeyRepo, principalRequestHeader);

        apiKeyFoundFilter.setAuthenticationManager(new AuthenticationManager() {

            @Override
            public Authentication authenticate(
                                        final Authentication authentication)
                                        throws AuthenticationException {
                ApiKey principal = (ApiKey) authentication.getPrincipal();
                if (principal == null) {
                    logger.warn("Attempted Access From Invalid Key");

                    throw new BadCredentialsException(
                        "The API key was not found or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });

        httpSecurity.antMatcher(ShortUrlController.BASE_PATH + "**")
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilter(apiKeyFoundFilter).authorizeRequests()
            .anyRequest().authenticated();
    }
}
