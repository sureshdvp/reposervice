package com.coditas.reposervice.service;

import com.coditas.reposervice.entity.github.model.GitHubJsonEntity;
import com.coditas.reposervice.entity.gitlab.model.GitLabJsonEntity;

import java.util.List;

public interface IRestServiceClient {
    public List<GitHubJsonEntity> executeGitHubService(String user, String authorization);
    public List<GitLabJsonEntity>  executeGitLabService(String user, String authorization);
}
