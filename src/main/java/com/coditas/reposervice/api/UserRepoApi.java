package com.coditas.reposervice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coditas.reposervice.github.model.GitHubJsonEntity;
import com.coditas.reposervice.service.UserRepoService;

@RestController
public class UserRepoApi {
	
	@Autowired
	private UserRepoService userRepoService;
	
	@GetMapping("/getRepo")
	public List<GitHubJsonEntity> getRepo() {
		return userRepoService.getUserRepos();
		
	}

}
