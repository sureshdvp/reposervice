package com.coditas.reposervice.gitlab.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitLabJsonEntity
{
    private int id;

    private String description;

    private String name;

    private String name_with_namespace;

    private String path;

    private String path_with_namespace;

    private String created_at;

    private String default_branch;

    private List<String> tag_list;

    private String ssh_url_to_repo;

    private String http_url_to_repo;

    private String web_url;

    private String readme_url;

    private String avatar_url;

    private int star_count;

    private int forks_count;

    private String last_activity_at;

    private Namespace namespace;

   
}