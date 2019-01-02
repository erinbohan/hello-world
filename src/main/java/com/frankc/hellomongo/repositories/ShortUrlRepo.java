package com.frankc.hellomongo.repositories;

import java.util.List;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

public interface ShortUrlRepo {

    ShortUrl newShortUrlEntity();

    ShortUrl save(ShortUrl link);

    ShortUrl find(String shortUrl) throws ShortUrlNotFoundException;

    List<? extends ShortUrl> findAllShortUrl();
}
