/*******************************************************************************
 * Copyright (C) 2019 Frank Callaly
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
