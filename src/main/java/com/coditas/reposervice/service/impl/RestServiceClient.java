package com.coditas.reposervice.service.impl;

import com.coditas.reposervice.entity.github.model.GitHubJsonEntity;
import com.coditas.reposervice.entity.gitlab.model.GitLabJsonEntity;
import com.coditas.reposervice.exception.RepositoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class RestServiceClient {

    @Value("${provider-service.gitHub.baseUrl:#{null}}")
    private String gitHubBaseUrl;

    @Value("${provider-service.gitHub.endpointUrl:#{null}}")
    private String gitHubEndpointUrl;

    @Value("${provider-service.gitLab.baseUrl:#{null}}")
    private String gitLabBaseUrl;

    @Value("${provider-service.gitLab.endpointUrl:#{null}}")
    private String gitLabEndpointUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RestServiceClient.class);


    public List<GitHubJsonEntity> invokeGitHubService(String user, String authorization) {
        try {
            ResponseEntity<List<GitHubJsonEntity>> responseEntity = restTemplate.exchange(getGitHubUrl(user), HttpMethod.GET, getRequestEntity(authorization), new ParameterizedTypeReference<List<GitHubJsonEntity>>() {
            });
            return responseEntity.getBody();
        } catch (Exception ex) {
            logger.warn("[RestServiceClient] Error occured while executing invokeGitHubService");
            if(ex.getMessage().contains("404")){
                throw new RepositoryNotFoundException(HttpStatus.NOT_FOUND, ex.getMessage());
            }
            throw ex;
        }


    }


    public List<GitLabJsonEntity> invokeGitLabService(String user, String authorization) {
        try {
            ResponseEntity<List<GitLabJsonEntity>> responseEntity = restTemplate.exchange(getGitLabUrl(user), HttpMethod.GET, getRequestEntity(authorization), new ParameterizedTypeReference<List<GitLabJsonEntity>>() {
            });
            return responseEntity.getBody();
        } catch (Exception ex) {
            logger.warn("[RestServiceClient] Error occured while executing invokeGitLabService");
            if(ex.getMessage().contains("404")){
                throw new RepositoryNotFoundException(HttpStatus.NOT_FOUND, ex.getMessage());
            }
            throw  ex;
        }


    }

    private HttpEntity<String> getRequestEntity(String authorization) {
        if (!StringUtils.isEmpty(authorization)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("authorization", authorization);
            return new HttpEntity<>(httpHeaders);
        }
        return null;
    }

    private String getGitHubUrl(String user) {
        String endpointUrl = gitHubEndpointUrl.replace("placeholder", user);
        return gitHubBaseUrl + endpointUrl;

    }

    private String getGitLabUrl(String user) {
        String endpointUrl = gitLabEndpointUrl.replace("placeholder", user);
        return gitLabBaseUrl + endpointUrl;

    }
}
