package com.frankc.hellomongo.repositories.impls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlDto;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

@Repository
@Profile("default")
public class InMemShortUrlRepo implements ShortUrlRepo {

    private final HashMap<String, ShortUrl> shortUrls =
                          new HashMap<String, ShortUrl>();

    public ShortUrl newShortUrlEntity() {
        return new ShortUrlDto();
    }

    public ShortUrl save(final ShortUrl link) {
        this.shortUrls.put(link.getShortUrl(), link);
        return link;
    }

    public ShortUrl find(final String shortUrl)
                                throws ShortUrlNotFoundException {
        ShortUrl requestedLink = this.shortUrls.get(shortUrl);

        if (requestedLink == null) {
            throw new ShortUrlNotFoundException();
        }
        return requestedLink;
    }

    public List<? extends ShortUrl> findAllShortUrl() {
        return new ArrayList<ShortUrl>(this.shortUrls.values());
    }
}
