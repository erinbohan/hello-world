package com.frankc.hellomongo.entities;

import io.swagger.annotations.ApiModelProperty;

public class ShortUrlDto implements ShortUrl {

    private String shortUrl;

    private String redirectTo;

    private ApiKey creatorApiKey;

    public ShortUrlDto() { }

    public ShortUrlDto(final String redirectTo) {
        this.setRedirectTo(redirectTo);
    }

    @ApiModelProperty(hidden = true)
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

    @ApiModelProperty(hidden = true)
    public String getAccountName() {
        return creatorApiKey.getContactName();
    }

    public void setCreatorApiKey(final ApiKey creatorApiKey) {
        this.creatorApiKey = creatorApiKey;
    }

    @ApiModelProperty(hidden = true)
    public ApiKey getCreatorApiKey() {
        return this.creatorApiKey;
    }

    public String toString() {
        return "shortUrl: " + this.getShortUrl()
               + ", redirectTo: " + this.getRedirectTo();
    }
}
