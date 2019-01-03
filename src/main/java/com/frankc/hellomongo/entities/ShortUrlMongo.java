package com.frankc.hellomongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class ShortUrlMongo implements ShortUrl {

    @JsonIgnore
    @Id
    private String id;

    @TextIndexed
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

    public String toString() {
        return "Id: " + this.getId()
               + ", shortUrl: " + this.getShortUrl()
               + ", redirectTo: " + this.getRedirectTo();
    }
}
