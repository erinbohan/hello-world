package com.frankc.hellomongo.entities;

import org.springframework.data.annotation.Id;

public class ShortUrlMongo implements ShortUrl {

    @Id
    private String id;

    private String shortUrl;

    private String redirectTo;

    public ShortUrlMongo() { }

    public ShortUrlMongo(final String shortUrl, final String redirectTo) {
        this.setShortUrl(shortUrl);
        this.setRedirectTo(redirectTo);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(final String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getRedirectTo() {
        return this.redirectTo;
    }

    public void setRedirectTo(final String redirectTo) {
        this.redirectTo = redirectTo;
    }
}
