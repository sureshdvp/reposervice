package com.coditas.reposervice.github.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GitHubRootJson {

	private List<GitHubJsonEntity> gitHubJsonEntities;


}