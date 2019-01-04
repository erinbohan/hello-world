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

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.entities.ShortUrl;

public interface ShortUrlService {

    ShortUrl createShortUrl(String redirectTo,
                            ApiKey apiKey);

    ShortUrl saveShortUrl(ShortUrl shortUrl);

    List<? extends ShortUrl> findAll();

    ShortUrl findByShortUrl(String shortUrl);

    void deleteByShortUrl(String shortUrl);
}
