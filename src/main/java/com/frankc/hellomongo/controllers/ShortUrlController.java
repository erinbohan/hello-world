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
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlDto;
import com.frankc.hellomongo.services.ShortUrlService;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

/**
 * Implements interface from RESTful HTTP/JSON to ShortUrl Repository.
 *
 * @author Frank Callaly
 */
@RestController
@RequestMapping(ShortUrlController.BASE_PATH)
public class ShortUrlController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String BASE_PATH = "/short-urls/";

    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * Get a collection of all ShortUrls in the repository.
     *
     * @return a List of ShortUrls
     */
    @GetMapping
    public List<? extends ShortUrl> getAllShortUrls(
                                    final HttpServletRequest request) {
        return shortUrlService.findAll();
    }

    /**
     * Get a single particular ShortUrl from the repository.
     *
     * @param shortUrl of ShortUrl to return, taken from path
     * @return a Resource<ShortUrl> corresponding to the given id
     */
    @GetMapping("{shortUrl}")
    public Resource<ShortUrl> getShortUrl(
                                @PathVariable("shortUrl") final String shortUrl,
                                final HttpServletRequest request)
                                throws ShortUrlNotFoundException {
        Resource<ShortUrl> resource;

        try {
            resource = new Resource<ShortUrl>(
                                shortUrlService.findByShortUrl(shortUrl));
        } catch (NoSuchElementException ex) {
            logger.warn("Attempt to find non-existing short url: " + shortUrl);
            throw new ShortUrlNotFoundException();
        }
        resource.add(linkTo(ShortUrlController.class)
                     .slash(shortUrl).withSelfRel());
        resource.add(linkTo(methodOn(this.getClass()).getAllShortUrls(request))
                     .withRel("all-urls"));

        return resource;
    }

    /**
     * Create a new ShortUrl and add to the repository.
     *
     * @param newShortUrl ShortUrl corresponding to data sent in request body
     * @return a Resource<ShortUrl> for the object that has been added
     */
    @PostMapping
    public Resource<ShortUrl> createShortUrl(
                                   @RequestBody final ShortUrlDto newShortUrl,
                                   final HttpServletRequest request,
                                   final HttpServletResponse response,
                                   final Authentication auth) {
        ShortUrl createdUrl = shortUrlService.createShortUrl(
                                                newShortUrl.getRedirectTo(),
                                                (ApiKey) auth.getPrincipal());

        response.setStatus(HttpServletResponse.SC_CREATED);

        Resource<ShortUrl> resource = new Resource<ShortUrl>(createdUrl);
        resource.add(linkTo(ShortUrlController.class)
                     .slash(createdUrl.getShortUrl()).withSelfRel());
        resource.add(linkTo(methodOn(this.getClass())
                     .getAllShortUrls(request)).withRel("all-urls"));

        return resource;
    }

    @DeleteMapping("{shortUrl}")
    public void deleteShortUrl(@PathVariable("shortUrl") final String shortUrl)
                                throws ShortUrlNotFoundException {
        shortUrlService.deleteByShortUrl(shortUrl);
    }
}
