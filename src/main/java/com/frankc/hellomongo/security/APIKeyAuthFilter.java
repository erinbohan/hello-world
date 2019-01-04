package com.frankc.hellomongo.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.frankc.hellomongo.repositories.ApiKeyRepoJpa;
import com.frankc.hellomongo.apikey.KeyNotFoundException;

public class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private ApiKeyRepoJpa apiKeyRepo;

    private String principalRequestHeader;

    public APIKeyAuthFilter(final ApiKeyRepoJpa apiKeyRepo,
                            final String principalRequestHeader) {
        this.apiKeyRepo = apiKeyRepo;
        this.principalRequestHeader = principalRequestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(
                                final HttpServletRequest request) {
        try {
            return apiKeyRepo.findByApiKey(
                            request.getHeader(principalRequestHeader));
        } catch (KeyNotFoundException ex) {
            logger.warn("API KEY Not Found: " + ex.getMessage());
        }
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(
                                final HttpServletRequest request) {
        return "N/A";
    }

}
