package com.frankc.hellomongo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ShortUrlJpa implements ShortUrl {

    private static final int UUID2_FIELD_SIZE = 36;

    @JsonIgnore
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, length = UUID2_FIELD_SIZE)
    private String shortUrl;

    private String redirectTo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = true)
    private ApiKey creatorApiKey;

    public ShortUrlJpa() { }

    public ShortUrlJpa(final String redirectTo) {
        this.setRedirectTo(redirectTo);
    }

    public ShortUrlJpa(final ShortUrlDto shortUrlDTO) {
        this.setRedirectTo(shortUrlDTO.getRedirectTo());
        this.setShortUrl(shortUrlDTO.getShortUrl());
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
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

    public ApiKey getCreatorApiKey() {
        return creatorApiKey;
    }

    public void setCreatorApiKey(final ApiKey creatorApiKey) {
        this.creatorApiKey = creatorApiKey;
    }

    public String getAccountName() {
        return this.creatorApiKey.getContactName();
    }

    public String toString() {
        return "Id: " + this.getId()
               + ", shortUrl: " + this.getShortUrl()
               + ", redirectTo: " + this.getRedirectTo();
    }

}
