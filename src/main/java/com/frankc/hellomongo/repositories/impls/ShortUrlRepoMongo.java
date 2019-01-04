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

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlMongo;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

@Repository
@Profile("mongo")
public interface ShortUrlRepoMongo
                    extends MongoRepository<ShortUrlMongo, String>,
                            ShortUrlRepo {

    default ShortUrl newShortUrlEntity() {
        return new ShortUrlMongo();
    }

    default List<? extends ShortUrl> findAllShortUrl() {
        return findAll();
    }

    List<ShortUrl> findByShortUrl(String shortUrl);

    default ShortUrl find(final String shortUrl)
                          throws ShortUrlNotFoundException {
        try {
            return findByShortUrl(shortUrl).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ShortUrlNotFoundException();
        }
    }
    default void deleteByShortUrlPath(final String shortUrlPath)
                                      throws ShortUrlNotFoundException {
        try {
            delete((ShortUrlMongo) findByShortUrl(shortUrlPath).get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new ShortUrlNotFoundException();
        }
}
}
