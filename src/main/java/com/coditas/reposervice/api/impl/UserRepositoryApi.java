package com.coditas.reposervice.api.impl;

import com.coditas.reposervice.api.IUserRepositoryApi;
import com.coditas.reposervice.entity.AggregateResponse;
import com.coditas.reposervice.service.IUserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRepositoryApi implements IUserRepositoryApi {

    @Autowired
    private IUserRepositoryService userRepositoryService;

    @Override
    public ResponseEntity<AggregateResponse> getReposiotories(String user, String authorization) {
        return new ResponseEntity<>(userRepositoryService.getRepositories(user, authorization), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<AggregateResponse> getOwnReposiotories(String user, String authorization) {
        return new ResponseEntity<>(userRepositoryService.getOwnRepositories(user, authorization), HttpStatus.OK);
    }
}
