package com.GitLookupServiceAsync;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.GitLookupServiceAsync.Model.User;
import com.GitLookupServiceAsync.Service.GitHubLookupService;

public class AppRunner implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	
	private final GitHubLookupService gitHubLookupService;
	
	public AppRunner(GitHubLookupService gitHubLookupService) {
		this.gitHubLookupService = gitHubLookupService;
	}

	@Override
	public void run(String... args) throws Exception {
		// start the clock
		long start = System.currentTimeMillis();
		
		// kick of multiple, asynchronous lookups
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
		CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
		CompletableFuture<User> page4 = gitHubLookupService.findUser("MathiasDaRex");
		
		// Wait till they all done
		CompletableFuture.allOf(page1,page2,page3,page4).join();
		
		// print out results including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + page1.get());
		logger.info("--> " + page2.get());
		logger.info("--> " + page3.get());
		logger.info("--> " + page4.get());
		
		
	}
	
	
	

}
