package com.frankc.hellomongo.entities;

public class ShortUrlDto implements ShortUrl {

    private String shortUrl;

    private String redirectTo;

    public ShortUrlDto() { }

    public ShortUrlDto(final String redirectTo) {
        this.setRedirectTo(redirectTo);
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
        return "shortUrl: " + this.getShortUrl()
               + ", redirectTo: " + this.getRedirectTo();
    }
}
