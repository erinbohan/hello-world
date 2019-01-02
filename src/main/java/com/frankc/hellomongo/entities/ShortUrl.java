package com.frankc.hellomongo.entities;

public interface ShortUrl {
    //String getId();
    //void setId(String id);

    String getShortUrl();
    void setShortUrl(String shortUrl);

    String getRedirectTo();
    void setRedirectTo(String redirectTo);
}
