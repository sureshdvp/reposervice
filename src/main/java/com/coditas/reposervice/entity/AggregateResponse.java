package com.coditas.reposervice.entity;

import com.coditas.reposervice.entity.github.model.GitHubRepoDTO;
import com.coditas.reposervice.entity.gitlab.model.GitLabRepoDTO;

import java.util.List;

public class AggregateResponse {

    private List<GitHubRepoDTO> gitHubRepositories;
    private List<GitLabRepoDTO> gitLabRepositories;

    public List<GitHubRepoDTO> getGitHubRepositories() {
        return gitHubRepositories;
    }

    public void setGitHubRepositories(List<GitHubRepoDTO> gitHubRepositories) {
        this.gitHubRepositories = gitHubRepositories;
    }

    public List<GitLabRepoDTO> getGitLabRepositories() {
        return gitLabRepositories;
    }

    public void setGitLabRepositories(List<GitLabRepoDTO> gitLabRepositories) {
        this.gitLabRepositories = gitLabRepositories;
    }
}
