package com.frankc.hellomongo.utils;

import java.util.regex.Pattern;

public class ShortUrlStringUtils {

    private static final String DEFAULT_URL_PROTOCOL = "http://";

    private static final Pattern URL_WITH_PROTOCOL =
            Pattern.compile("(?i)^(https?|ftp|file)://.*$");

    public static String fixUrlProtocol(final String url) {
        if (URL_WITH_PROTOCOL.matcher(url).matches()) {
            return url;
        }
        return DEFAULT_URL_PROTOCOL + url;
    }
}
