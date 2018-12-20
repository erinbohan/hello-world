package com.frankc.hellomongo.shortlink.inmem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.shortlink.LinkNotFoundException;
import com.frankc.hellomongo.shortlink.ShortLink;
import com.frankc.hellomongo.shortlink.ShortLinkRepo;

@Repository
@Profile("default")
public class InMemShortLinkRepo implements ShortLinkRepo {

    private AtomicLong counter = new AtomicLong();
    private final HashMap<String, ShortLink> links =
                          new HashMap<String, ShortLink>();

    public ShortLink save(final ShortLink link) {
        link.setId(Long.toString(counter.incrementAndGet()));
        this.links.put(link.getId(), link);
        return link;
    }

    public ShortLink find(final String id) throws LinkNotFoundException {
        ShortLink requestedLink = this.links.get(id);

        if (requestedLink == null) {
            throw new LinkNotFoundException();
        }
        return requestedLink;
    }

    public List<ShortLink> findAll() {
        return new ArrayList<ShortLink>(this.links.values());
    }
}
