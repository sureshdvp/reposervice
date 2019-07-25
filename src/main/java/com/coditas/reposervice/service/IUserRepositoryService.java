package com.coditas.reposervice.service;


import com.coditas.reposervice.entity.AggregateResponse;


public interface IUserRepositoryService {

    AggregateResponse getRepositories(String user, String authorization);

    AggregateResponse getOwnRepositories(String user, String authorization);

}


