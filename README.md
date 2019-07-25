Introduction 
==============
The objective of the this repository (repositorservice) is to provide aggregated response. The aggregated response contains user repositories information from git hub and git Lab.

How it works ?
==================
when user calls this service using git hub username and git lab user name, this service internally calls git hub and git lab services and fetch repositories details of the specified user from both git hub and git lab.

Following are the API services exposed from this repository.

1. reposervice/{username}/repositories  
2. reposervice/{username}/ownrepositories    

The above first API service fetches the reposotories from both git hub and git lab. 
these repositories either created by the given user or user might have contributed(committed some changes) to these repositories.

The above second API service fetches the reposotories from both git hub and git lab.these repositories are belongs to the given user and given user is the owner for that repository.

Provider-APIS
===============

Following are Provider APIs which used by above 2 APIs services to fetch the repositories from both git hub and git lab.

GitHub provider API
===================

 https://api.github.com/users/{userName}/repos

GitLab provider API
====================

https://gitlab.com/api/v4/users/{UserName}/projects


Implementation:
===============

Functionality has been implemented by using spring boot , Rest controller and Rest Template.

How to build and run
===========================
Maven is the build tool  used for this repository and  jar file can be created running commands like mvn clean insall.

When this repository is cloned into local machine it can run on IDE

Further improvements
==========================================

improvement 1
==============

There are limitations when this service calls github and gitlab provider apis and provider can allow only 60 API calls from one IP address per day but when authorization(username and password) information are passed it works fine.

without authorization 60 calls are allowed per day and with authorization there is no limitation.

functionality has been implemented for both cases

if given user does not want to provide authorization information then we can provide alterantive solution by implementing cache (Spring cacheManager or custom implementation with Concurrent hash map).

a back ground thread runs for each 10 mins or 15 mins periodically and it calls the provider apis and fetch the repositories information and store it into cache. if given user hits the same subsequently, data is fetched from cache.

By implementing cache we can get 2 benfits
1. we can avoid facing issue of limitation of 60 api calls per day
2. we can also get performance improvement


improvement 2
=============
I provided the basic exception handling with @ControllerAdvice and @ExceptionHandler but it should be enhanced to cover all the negative cases.

improvement 3
==============
Junit and funcationality tests should be be written















