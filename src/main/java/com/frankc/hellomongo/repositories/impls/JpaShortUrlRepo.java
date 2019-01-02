package com.frankc.hellomongo.repositories.impls;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ShortUrl;
import com.frankc.hellomongo.entities.ShortUrlJpa;
import com.frankc.hellomongo.repositories.ShortUrlRepo;
import com.frankc.hellomongo.shorturl.ShortUrlNotFoundException;

@Repository
@Profile("jpa")
public interface JpaShortUrlRepo
                    extends JpaRepository<ShortUrlJpa, String>,
                            ShortUrlRepo {

    default ShortUrl newShortUrlEntity() {
        return new ShortUrlJpa();
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
