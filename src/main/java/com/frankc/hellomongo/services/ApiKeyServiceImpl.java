/*******************************************************************************
 * Copyright (C) 2019 Frank Callaly
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
