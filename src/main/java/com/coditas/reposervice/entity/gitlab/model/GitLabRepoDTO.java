package com.coditas.reposervice.entity.gitlab.model;

public class GitLabRepoDTO {

    private String name;

    private String description;

    private String name_with_namespace;

    private String path;

    private String path_with_namespace;

    private String created_at;

    private String http_url_to_repo;

    private String web_url;

    private String readme_url;

    private int star_count;

    private int forks_count;

    private String last_activity_at;

   private  NameSpaceDTO nameSpace;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName_with_namespace(String name_with_namespace) {
        this.name_with_namespace = name_with_namespace;
    }

    public String getName_with_namespace() {
        return this.name_with_namespace;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath_with_namespace(String path_with_namespace) {
        this.path_with_namespace = path_with_namespace;
    }

    public String getPath_with_namespace() {
        return this.path_with_namespace;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setHttp_url_to_repo(String http_url_to_repo) {
        this.http_url_to_repo = http_url_to_repo;
    }

    public String getHttp_url_to_repo() {
        return this.http_url_to_repo;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getWeb_url() {
        return this.web_url;
    }

    public void setReadme_url(String readme_url) {
        this.readme_url = readme_url;
    }

    public String getReadme_url() {
        return this.readme_url;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public int getStar_count() {
        return this.star_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public int getForks_count() {
        return this.forks_count;
    }

    public void setLast_activity_at(String last_activity_at) {
        this.last_activity_at = last_activity_at;
    }

    public String getLast_activity_at() {
        return this.last_activity_at;
    }

    public NameSpaceDTO getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(NameSpaceDTO nameSpace) {
        this.nameSpace = nameSpace;
    }
}


