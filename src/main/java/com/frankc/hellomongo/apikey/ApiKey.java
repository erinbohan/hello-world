package com.frankc.hellomongo.apikey;

public class ApiKey {

    private String id;
    private String key;

    public ApiKey(final String key) {
        this.setKey(key);
    }

    public ApiKey(final String id, final String key) {
        this.setId(id);
        this.setKey(key);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }
}
