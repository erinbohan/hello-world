/*******************************************************************************
 * Copyright (C) 2019 Frank Callaly
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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

    public void setCreatorApiKey(final ApiKey creatorApiKey) {
        return;
    }

    public String getAccountName() {
        return "";
    }

    public String toString() {
        return "Id: " + this.getId()
               + ", shortUrl: " + this.getShortUrl()
               + ", redirectTo: " + this.getRedirectTo();
    }
}
