package com.frankc.hellomongo.shortlink;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frankc.hellomongo.shorturl.ShortUrlGenerator;

public class ShortUrlGeneratorTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testUrlCreated() {
        String newShortUrl = new ShortUrlGenerator().generateBase62();
        logger.debug("URL Generated: " + newShortUrl);

        assertThat(newShortUrl, is(not(isEmptyOrNullString())));
    }

}
