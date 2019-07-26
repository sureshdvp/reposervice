package com.coditas.reposervice.service.impl;

import com.coditas.reposervice.entity.AggregateResponse;
import com.coditas.reposervice.entity.github.model.GitHubJsonEntity;
import com.coditas.reposervice.entity.github.model.GitHubRepoDTO;
import com.coditas.reposervice.entity.gitlab.model.GitLabJsonEntity;
import com.coditas.reposervice.entity.gitlab.model.GitLabRepoDTO;
import com.coditas.reposervice.exception.RepositoryNotFoundException;
import com.coditas.reposervice.service.IUserRepositoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class UserRepositoryServiceImpl implements IUserRepositoryService {

    @Autowired
    private RestServiceClient restServiceClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExecutorService executorService;


    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryServiceImpl.class);

    /**
     * This method calls provider services and aggreate the
     * repsonse recieved from the provider
     * @param user
     * @param authorization
     * @return AggregateResponse
     */
    @Override
    public AggregateResponse getRepositories(String user, String authorization) {

        logger.info("[UserRepositoryServiceImpl] execution started for getRepositories");
        AggregateResponse aggregateResponse = new AggregateResponse();
        //Asynchronous call to invoke github sercie
        Future<List<GitHubRepoDTO>> gitHubRepoDTOFuture = executorService.submit(() -> invokeAysncGitHubService(user, authorization));
        List<GitLabJsonEntity> gitLabJsonEntities = restServiceClient.invokeGitLabService(user, authorization);
        List<GitHubRepoDTO> gitHubRepoDTOS = null;
        try{
            gitHubRepoDTOS = gitHubRepoDTOFuture.get();
            aggregateResponse.setGitHubRepositories(gitHubRepoDTOS);
        }catch (Exception ex){
            logger.error("[UserRepositoryServiceImpl] Error while getting gitHubRepoDTO from async call future. Stack trace = {}", ex);
        }
        if (!ObjectUtils.isEmpty(gitLabJsonEntities)) {
            aggregateResponse.setGitLabRepositories(mapToGitLabRepoDTO(gitLabJsonEntities));
        } else {
            logger.warn("[UserRepositoryServiceImpl] Repository is not found in git bub for the user {}", user);
        }

        if (ObjectUtils.isEmpty(gitLabJsonEntities) && ObjectUtils.isEmpty(gitHubRepoDTOS)) {
            logger.error("[UserRepositoryServiceImpl] Repository is not found in git hub and gitlab for the user {}", user);
            throw new RepositoryNotFoundException(HttpStatus.NOT_FOUND, "Repository is not found in git hub and gitlab for the user " + user);
        }
        logger.info("[UserRepositoryServiceImpl] execution completed for getRepositories");
        return aggregateResponse;
    }

    /**
     * This method calls provider services and filter
     * the response for own repositories and aggreage
     * the repsonse
     * @param user
     * @param authorization
     * @return AggregateResponse
     */
    @Override
    public AggregateResponse getOwnRepositories(String user, String authorization) {
        logger.info("[UserRepositoryServiceImpl] execution started for getOwnRepositories");
        AggregateResponse aggregateResponse = new AggregateResponse();

        Future<List<GitHubJsonEntity>> gitHubJsonEntityFuture = executorService.submit(() -> restServiceClient.invokeGitHubService(user, authorization));
        List<GitHubJsonEntity> gitHubJsonEntities = null;
        try {
            gitHubJsonEntities = gitHubJsonEntityFuture.get();
        }catch (Exception ex){
            logger.error("[UserRepositoryServiceImpl] Error while getting gitHubJsonEntities from async call. stack trace = {}",ex);
        }
        List<GitLabJsonEntity> gitLabJsonEntities = restServiceClient.invokeGitLabService(user, authorization);
        List<GitLabJsonEntity> gitLabOwnRepositories = null;
        if (!ObjectUtils.isEmpty(gitLabJsonEntities)) {
            gitLabOwnRepositories = gitLabJsonEntities.stream()
                    .filter(gitLabJsonEntity -> !ObjectUtils.isEmpty(gitLabJsonEntity.getNamespace()) && user.equals(gitLabJsonEntity.getNamespace().getPath()))
                    .collect(Collectors.toList());
            if (!ObjectUtils.isEmpty(gitLabOwnRepositories)) {
                aggregateResponse.setGitLabRepositories(mapToGitLabRepoDTO(gitLabOwnRepositories));
            } else {
                logger.warn("Own Repository is not found in git Lab for the user {}", user);
            }

        }
        List<GitHubJsonEntity> gitHubOwnRepositories = null;
        if (!ObjectUtils.isEmpty(gitHubJsonEntities)) {
            gitHubOwnRepositories = gitHubJsonEntities.stream()
                    .filter(gitLabJsonEntity -> !ObjectUtils.isEmpty(gitLabJsonEntity.getOwner()) && user.equals(gitLabJsonEntity.getOwner().getLogin()))
                    .collect(Collectors.toList());
            if (!ObjectUtils.isEmpty(gitHubOwnRepositories)) {
                aggregateResponse.setGitHubRepositories(mapToGitHubRepoDTO(gitHubOwnRepositories));
            } else {
                logger.warn("[UserRepositoryServiceImpl] Own Repository is not found in git hub for the user {}", user);
            }
        }

        if (ObjectUtils.isEmpty(gitHubJsonEntities) && ObjectUtils.isEmpty(gitLabJsonEntities)) {
            logger.warn("[UserRepositoryServiceImpl] Repository is not found in git hub and gitlab for the user {}", user);
            throw new RepositoryNotFoundException(HttpStatus.NOT_FOUND, "Repository is not found in git hub and gitlab for the user " + user);
        }
        return aggregateResponse;
    }


    private List<GitHubRepoDTO> mapToGitHubRepoDTO(List<GitHubJsonEntity> gitHubJsonEntities) {
        List<GitHubRepoDTO> gitHubRepositories = modelMapper.map(gitHubJsonEntities, new TypeToken<List<GitHubRepoDTO>>() {
        }.getType());
        return gitHubRepositories;
    }

    private List<GitLabRepoDTO> mapToGitLabRepoDTO(List<GitLabJsonEntity> gitLabJsonEntities) {
        List<GitLabRepoDTO> gitLabRepositories = modelMapper.map(gitLabJsonEntities, new TypeToken<List<GitLabRepoDTO>>() {
        }.getType());
        return gitLabRepositories;

    }

    private List<GitHubRepoDTO> invokeAysncGitHubService(String user,String authorization) {
        List<GitHubRepoDTO> gitHubRepoDTOS = null;
        List<GitHubJsonEntity> gitHubJsonEntities = restServiceClient.invokeGitHubService(user, authorization);
        if (!ObjectUtils.isEmpty(gitHubJsonEntities)) {
            gitHubRepoDTOS = mapToGitHubRepoDTO(gitHubJsonEntities);
        } else {
            logger.warn("[UserRepositoryServiceImpl] Repository is not found in git bub for the user {}", user);
        }
        return gitHubRepoDTOS;
    }

}


