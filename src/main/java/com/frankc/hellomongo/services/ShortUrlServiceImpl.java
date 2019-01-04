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
package com.frankc.hellomongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlGenerator;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepo shortUrlRepo;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    public ShortUrl createShortUrl(final String redirectTo,
                                   final ApiKey creatorApiKey) {
        ShortUrl newShortUrl = shortUrlRepo.newShortUrlEntity();
        newShortUrl.setRedirectTo(redirectTo);
        newShortUrl.setShortUrl(shortUrlGenerator.generateBase62());
        newShortUrl.setCreatorApiKey(creatorApiKey);
        return shortUrlRepo.save(newShortUrl);
    }

    public ShortUrl saveShortUrl(final ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    public List<? extends ShortUrl> findAll() {
        return shortUrlRepo.findAllShortUrl();
    }

    public ShortUrl findByShortUrl(final String shortUrl) {
        return shortUrlRepo.find(shortUrl);
    }

    public void deleteByShortUrl(final String shortUrlPath) {
        shortUrlRepo.deleteByShortUrlPath(shortUrlPath);
        return;
    }
}
