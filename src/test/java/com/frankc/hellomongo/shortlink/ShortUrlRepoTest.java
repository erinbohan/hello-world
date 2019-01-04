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
package com.frankc.hellomongo.shortlink;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.frankc.hellomongo.entities.ShortUrlJpa;
import com.frankc.hellomongo.repositories.ShortUrlRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortUrlRepoTest {

    @Autowired
    private ShortUrlRepo shortLinkRepo;

    @Test
    public void testShortLinkRepoAdd() {
        ShortUrlJpa newShortLink = new ShortUrlJpa();
        newShortLink.setRedirectTo("http://www.google.ie");
        shortLinkRepo.save(newShortLink);
        assertNotNull("ShortLinkRepo should return object just added",
                      shortLinkRepo.find(newShortLink.getShortUrl()));
    }
}
