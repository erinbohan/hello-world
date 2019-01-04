package com.frankc.hellomongo.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frankc.hellomongo.entities.ApiKey;


@Repository
@Profile("jpa")
public interface ApiKeyRepoJpa extends JpaRepository<ApiKey, Long> {

    ApiKey findByApiKey(String apiKey);
}
