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

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.services.ShortUrlService;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;
import com.frankc.hellomongo.utils.ShortUrlStringUtils;

/**
 * Implements interface from RESTful HTTP/JSON to ShortUrl Repository.
 *
 * @author Frank Callaly
 */
@RestController
public class ShortUrlRedirectController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * Redirect to the redirectTo field of a ShortUrl.
     *
     * @param shortUrl of url to be redirected to
     * @param response object to set status and header
     * @throws ShortUrlNotFoundException
     */
    @GetMapping("{shortUrl:[a-zA-Z0-9]+$}")
    public void execShortUrl(@PathVariable("shortUrl") final String shortUrl,
                              final HttpServletResponse response)
            throws ShortUrlNotFoundException {
        ShortUrl requestedShortUrl = shortUrlService.findByShortUrl(shortUrl);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location",
                           ShortUrlStringUtils.fixUrlProtocol(
                                   requestedShortUrl.getRedirectTo()));

        logger.debug("Redirecting From [" + shortUrl + "] to ["
                     + requestedShortUrl.getRedirectTo() + "]");
        return;
    }
}
