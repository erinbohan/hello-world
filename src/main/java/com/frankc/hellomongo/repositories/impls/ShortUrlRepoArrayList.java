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
package com.frankc.hellomongo.repositories.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlDto;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

@Repository
@Profile("default")
public class ShortUrlRepoArrayList implements ShortUrlRepo {

    private final HashMap<String, ShortUrl> shortUrls =
                          new HashMap<String, ShortUrl>();

    public ShortUrl newShortUrlEntity() {
        return new ShortUrlDto();
    }

    public ShortUrl save(final ShortUrl link) {
        this.shortUrls.put(link.getShortUrl(), link);
        return link;
    }

    public ShortUrl find(final String shortUrl)
                                throws ShortUrlNotFoundException {
        ShortUrl requestedLink = this.shortUrls.get(shortUrl);

        if (requestedLink == null) {
            throw new ShortUrlNotFoundException();
        }
        return requestedLink;
    }

    public List<? extends ShortUrl> findAllShortUrl() {
        return new ArrayList<ShortUrl>(this.shortUrls.values());
    }

    public void deleteByShortUrlPath(final String shortUrlPath) {
        return;
    }
}
