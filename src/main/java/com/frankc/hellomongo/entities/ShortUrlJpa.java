package com.frankc.hellomongo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ShortUrlJpa implements ShortUrl {

    private static final int UUID2_FIELD_SIZE = 36;

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = UUID2_FIELD_SIZE)
    private String id;

    @Column(unique = true, length = UUID2_FIELD_SIZE)
    private String shortUrl;

    private String redirectTo;

    public ShortUrlJpa() { }

    public ShortUrlJpa(final String redirectTo) {
        this.setRedirectTo(redirectTo);
    }

    public ShortUrlJpa(final ShortUrlDto shortUrlDTO) {
        this.setRedirectTo(shortUrlDTO.getRedirectTo());
        this.setShortUrl(shortUrlDTO.getShortUrl());
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
