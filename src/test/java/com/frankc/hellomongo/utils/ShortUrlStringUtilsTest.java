package com.frankc.hellomongo.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShortUrlStringUtilsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testFixUrlProtocolWithProtocol() {
        String result;
        String url = "http://www.google.ie";

        logger.debug("Test standard url string:");
        result = ShortUrlStringUtils.fixUrlProtocol(url);
        logger.debug(result);
        assertTrue(result.equals(url));
    }

    @Test
    public void testFixUrlProtocolNoProtocol() {
        String result;
        String url = "www.google.ie";
        logger.debug("Test url string with no protcol:");
        result = ShortUrlStringUtils.fixUrlProtocol(url);
        logger.debug(result);
        assertTrue(result.equals("http://" + url));
    }

    @Test
    public void testFixUrlProtocolCaseInsensitivity() {
        String result;
        String url = "HTtP://www.google.ie";
        logger.debug("Test url string with changing case protocol:");
        result = ShortUrlStringUtils.fixUrlProtocol(url);
        logger.debug(result);
        assertTrue(result.equalsIgnoreCase(url));
    }
}
