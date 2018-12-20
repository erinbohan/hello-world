package com.frankc.hellomongo.shortlink.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.shortlink.ShortLink;
import com.frankc.hellomongo.shortlink.ShortLinkRepo;

@Repository
@Profile("jpa")
public interface JpaShortLinkRepo
                    extends JpaRepository<ShortLink, String>,
                            ShortLinkRepo {

    default ShortLink find(final String id) {
        return findById(id).get();
    }
}
