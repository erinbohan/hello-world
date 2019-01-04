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
package com.frankc.hellomongo.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.services.ApiKeyService;

/**
 * Implements interface from RESTful HTTP/JSON to ApiKey Repository.
 *
 * @author Frank Callaly
 */
@RestController
@RequestMapping(ApiKeyController.BASE_PATH)
public class ApiKeyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String BASE_PATH = "/api-keys/";

    @Autowired
    private ApiKeyService apiKeyService;

    /**
     * Get a collection of all ApiKeys in the repository.
     *
     * @return a List of ApiKeys
     */
    @GetMapping
    public List<ApiKey> findAllApiKeys() {
        return apiKeyService.findAll();
    }

    /**
     * Create a new ApiKey and add to the repository.
     *
     * @param newApiKey ApiKey corresponding to data sent in request body
     * @return a Resource<ApiKey> for the object that has been added
     */
    @PostMapping
    public Resource<ApiKey> createApiKey(
                                   @RequestBody final ApiKey newApiKey,
                                   final HttpServletRequest request,
                                   final HttpServletResponse response,
                                   final Authentication auth) {
        ApiKey createdApiKey = apiKeyService.createApiKey(newApiKey);

        logger.debug("createApiKey returning created: " + createdApiKey);

        response.setStatus(HttpServletResponse.SC_CREATED);

        Resource<ApiKey> resource = new Resource<ApiKey>(createdApiKey);
        resource.add(linkTo(ApiKeyController.class)
                     .slash(createdApiKey.getId()).withSelfRel());
        resource.add(linkTo(methodOn(this.getClass())
                     .findAllApiKeys()).withRel("all-urls"));

        return resource;
    }
}
