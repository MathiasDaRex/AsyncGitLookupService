package com.GitLookupServiceAsync.Service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.GitLookupServiceAsync.Model.User;

// This project has based on https://spring.io/guides/gs/async-method/ -- tutorial


// The GitHubLookupService class uses Spring’s RestTemplate to invoke a remote REST point (api.github.com/users/)
// and then convert the answer into a User object. Spring Boot automatically provides a RestTemplateBuilder that
// customizes the defaults with any auto-configuration bits (that is, MessageConverter).
//
// The class is marked with the @Service annotation, making it a candidate for Spring’s component scanning to detect
// and add to the application context.
//
// The findUser method is flagged with Spring’s @Async annotation, indicating that it should run on a separate thread.
// The method’s return type is CompletableFuture<User> instead of User, a requirement for any asynchronous service.
// This code uses the completedFuture method to return a CompletableFuture instance that is already completed with result of the GitHub query.
//
// Creating a local instance of the GitHubLookupService class does NOT allow the findUser method to run asynchronously.
// It must be created inside a @Configuration class or picked up by @ComponentScan.



@Service
public class GitHubLookupService {

	private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);
	
	private final RestTemplate restTemplate;
	
	public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException{
		logger.info("Looking up " + user);
		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Delay 1s for demonstration
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(results);
	}
}
