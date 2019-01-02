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
