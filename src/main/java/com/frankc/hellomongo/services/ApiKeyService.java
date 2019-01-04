package com.frankc.hellomongo.services;

import java.util.List;

import com.frankc.hellomongo.entities.ApiKey;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey newApiKey);

    List<ApiKey> findAll();
}
