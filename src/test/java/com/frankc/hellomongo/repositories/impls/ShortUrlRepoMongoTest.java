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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.frankc.hellomongo.entities.ShortUrl;

@RunWith(SpringRunner.class)
@ActiveProfiles("mongo")
@DataMongoTest
public class ShortUrlRepoMongoTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShortUrlRepoMongo shortUrlRepoMongo;

    @Before
    public void setup() {
    }

    @Test
    public void testShortUrlRepoSaveAndFindAll() {
        String testRedirectTo = "http://www.google.ie";
        String testShortUrlPath = "ABCDEFGHIJKLMNOP";
        ShortUrl newShortUrl = shortUrlRepoMongo.newShortUrlEntity();
        newShortUrl.setRedirectTo(testRedirectTo);
        newShortUrl.setShortUrl(testShortUrlPath);
        //testEntityManager.persist(newShortUrl);

        shortUrlRepoMongo.save(newShortUrl);
        List<? extends ShortUrl> foundShortUrls =
                                        shortUrlRepoMongo.findAllShortUrl();

        assertTrue(foundShortUrls.size() == 1);
        assertTrue(foundShortUrls.get(0).getRedirectTo()
                                        .equals(testRedirectTo));
        logger.info("Retrieved Short Url: " + foundShortUrls.get(0).toString());
    }
}
