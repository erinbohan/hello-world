package com.frankc.hellomongo.apikey;

public class ApiKey {

	private String id;
	private String key;

	public ApiKey(String key) {
		this.setKey(key);
	}

	public ApiKey(String id, String key) {
		this.setId(id);
		this.setKey(key);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}