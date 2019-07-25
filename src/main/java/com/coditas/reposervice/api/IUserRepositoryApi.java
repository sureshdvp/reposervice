package com.coditas.reposervice.api;

import com.coditas.reposervice.entity.AggregateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IUserRepositoryApi {

    @GetMapping("/{user}/repositories")
    ResponseEntity<AggregateResponse> getReposiotories(@PathVariable String user, @RequestHeader(value = "Authorization", required = false) String authorizatio);

    @GetMapping("/{user}/ownrepositories")
    ResponseEntity<AggregateResponse> getOwnReposiotories(@PathVariable String user, @RequestHeader(value = "Authorization", required = false) String authorizatio);
}
