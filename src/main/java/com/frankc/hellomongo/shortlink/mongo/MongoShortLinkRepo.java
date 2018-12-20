package com.frankc.hellomongo.shortlink.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.shortlink.ShortLink;
import com.frankc.hellomongo.shortlink.ShortLinkRepo;

@Repository
@Profile("mongo")
public interface MongoShortLinkRepo
                    extends MongoRepository<ShortLink, String>,
                            ShortLinkRepo {

    default ShortLink find(final String id) {
        return findById(id).get();
    }
}
