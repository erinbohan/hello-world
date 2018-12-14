package com.frankc.hellomongo.apikey;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class ApiKeyRepo {

	private AtomicLong counter = new AtomicLong();
	private final HashMap<String, ApiKey> apiKeys = new HashMap<String, ApiKey>();

	public ApiKeyRepo() {
		// Create some keys
		add(new ApiKey("FRANKS_KEY"));
	}

	public void add(ApiKey apiKey) {
		apiKey.setId(Long.toString(counter.incrementAndGet()));
		this.apiKeys.put(apiKey.getKey(), apiKey);
	}

	public ApiKey get(String apiKey) throws KeyNotFoundException {
		ApiKey requestedKey = this.apiKeys.getOrDefault(apiKey, null);

		if(requestedKey == null) {
			throw new KeyNotFoundException();
		}
		return requestedKey;
	}

	public List<ApiKey> getAll() {
		return new ArrayList<ApiKey>(this.apiKeys.values());
	}
}