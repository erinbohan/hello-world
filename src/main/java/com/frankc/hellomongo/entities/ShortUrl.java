package com.frankc.hellomongo.entities;

public interface ShortUrl {

    String getShortUrl();
    void setShortUrl(String shortUrl);

    String getRedirectTo();
    void setRedirectTo(String redirectTo);
}
