package com.frankc.hellomongo.repositories.impls;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlMongo;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

@Repository
@Profile("mongo")
public interface MongoShortUrlRepo
                    extends MongoRepository<ShortUrlMongo, String>,
                            ShortUrlRepo {

    default ShortUrl newShortUrlEntity() {
        return new ShortUrlMongo();
    }

    default List<? extends ShortUrl> findAllShortUrl() {
        return findAll();
    }

    List<ShortUrl> findByShortUrl(String shortUrl);

    default ShortUrl find(final String shortUrl)
                          throws ShortUrlNotFoundException {
        try {
            return findByShortUrl(shortUrl).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ShortUrlNotFoundException();
        }
    }
}
