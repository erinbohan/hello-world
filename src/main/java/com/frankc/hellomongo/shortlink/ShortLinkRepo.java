package com.frankc.hellomongo.shortlink;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class ShortLinkRepo {

	private AtomicLong counter = new AtomicLong();
	private final HashMap<String, ShortLink> links = new HashMap<String, ShortLink>();

	public void add(ShortLink link) {
		link.setId(Long.toString(counter.incrementAndGet()));
		this.links.put(link.getId(), link);
	}

	public ShortLink get(String id) throws LinkNotFoundException {
		ShortLink requestedLink = this.links.get(id);

		if(requestedLink == null) {
			throw new LinkNotFoundException();
		}
		return requestedLink;
	}

	public List<ShortLink> getAll() {
		return new ArrayList<ShortLink>(this.links.values());
	}
}