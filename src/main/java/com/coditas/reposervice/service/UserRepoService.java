package com.coditas.reposervice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coditas.reposervice.github.model.GitHubJsonEntity;

@Service
public class UserRepoService {
	
	@Autowired
	private RestTemplate restTemplate;

	public List<GitHubJsonEntity> getUserRepos() {
		String uri = "https://api.github.com/users/sureshdvp/repos";

	ResponseEntity<List<GitHubJsonEntity>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<GitHubJsonEntity>>() {});
	List<GitHubJsonEntity> gitHubJsonEntities = responseEntity.getBody();
	
	return gitHubJsonEntities;
	
	}

}
