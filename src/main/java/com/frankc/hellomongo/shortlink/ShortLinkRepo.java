package com.frankc.hellomongo.shortlink;

import java.util.List;

public interface ShortLinkRepo {
    ShortLink save(ShortLink link);

    ShortLink find(String id) throws LinkNotFoundException;

    List<ShortLink> findAll();
}
