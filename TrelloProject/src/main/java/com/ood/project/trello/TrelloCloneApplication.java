package com.ood.project.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TrelloCloneApplication {

	public static void main(String[] args) {
		System.out.println("Build Successful!");
		SpringApplication.run(TrelloCloneApplication.class, args);
	}

}
