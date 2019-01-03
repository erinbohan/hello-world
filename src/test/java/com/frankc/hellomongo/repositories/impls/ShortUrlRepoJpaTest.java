package com.frankc.hellomongo.repositories.impls;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.frankc.hellomongo.entities.ShortUrl;

@RunWith(SpringRunner.class)
@ActiveProfiles("jpa")
@DataJpaTest
public class ShortUrlRepoJpaTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ShortUrlRepoJpa shortUrlRepoJpa;

    @Before
    public void setup() {
    }

    @Test
    public void testShortUrlRepoFindAll() {
        String testRedirectTo = "http://www.google.ie";
        String testShortUrlPath = "ABCDEFGHIJKLMNOP";
        ShortUrl newShortUrl = shortUrlRepoJpa.newShortUrlEntity();
        newShortUrl.setRedirectTo(testRedirectTo);
        newShortUrl.setShortUrl(testShortUrlPath);
        testEntityManager.persist(newShortUrl);

        List<? extends ShortUrl> foundShortUrls =
                                        shortUrlRepoJpa.findAllShortUrl();

        assertTrue(foundShortUrls.size() == 1);
        assertTrue(foundShortUrls.get(0).getRedirectTo()
                                        .equals(testRedirectTo));
        logger.info("Retrieved Short Url: " + foundShortUrls.get(0).toString());
    }

}
