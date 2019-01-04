package com.frankc.hellomongo.services;

import java.util.List;

import com.frankc.hellomongo.entities.ApiKey;
import com.frankc.hellomongo.entities.ShortUrl;

public interface ShortUrlService {

    ShortUrl createShortUrl(String redirectTo,
                            ApiKey apiKey);

    ShortUrl saveShortUrl(ShortUrl shortUrl);

    List<? extends ShortUrl> findAll();

    ShortUrl findByShortUrl(String shortUrl);

    void deleteByShortUrl(String shortUrl);
}
