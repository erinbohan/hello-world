package com.frankc.hellomongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlGenerator;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private ShortUrlRepo shortUrlRepo;

    @Autowired
    private ShortUrlGenerator shortUrlGenerator;

    public ShortUrl createShortUrl(final String redirectTo) {
        ShortUrl newShortUrl = shortUrlRepo.newShortUrlEntity();
        newShortUrl.setRedirectTo(redirectTo);
        newShortUrl.setShortUrl(shortUrlGenerator.generateBase62());
        return shortUrlRepo.save(newShortUrl);
    }

    public ShortUrl saveShortUrl(final ShortUrl shortUrl) {
        return shortUrlRepo.save(shortUrl);
    }

    public List<? extends ShortUrl> findAll() {
        return shortUrlRepo.findAllShortUrl();
    }

    public ShortUrl findByShortUrl(final String shortUrl) {
        return shortUrlRepo.find(shortUrl);
    }

    public void deleteByShortUrl(final String shortUrlPath) {
        shortUrlRepo.deleteByShortUrlPath(shortUrlPath);
        return;
    }
}
