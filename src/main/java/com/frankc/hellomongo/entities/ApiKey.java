package com.frankc.hellomongo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue
    private long id;

    private String apiKey;

    private String contactName;

    private boolean isAdmin = false;

    public ApiKey() { }

    public ApiKey(final String apiKey,
                  final String contactName,
                  final boolean isAdmin) {
        this.setApiKey(apiKey);
        this.setContactName(contactName);
        this.setAdmin(isAdmin);
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
