package com.frankc.hellomongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frankc.hellomongo.repositories.ApiKeyRepoJpa;
import com.frankc.hellomongo.entities.ApiKey;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private ApiKeyRepoJpa apiKeyRepo;

    public ApiKey createApiKey(final ApiKey newApiKey) {
        newApiKey.setApiKey(newApiKey.getContactName().toUpperCase());
        apiKeyRepo.save(newApiKey);
        return newApiKey;
    }

    public List<ApiKey> findAll() {
        return apiKeyRepo.findAll();
    }
}
