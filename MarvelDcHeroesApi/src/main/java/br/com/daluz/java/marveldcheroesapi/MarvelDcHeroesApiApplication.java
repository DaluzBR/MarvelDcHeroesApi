package br.com.daluz.java.marveldcheroesapi;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class MarvelDcHeroesApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(MarvelDcHeroesApiApplication.class, args);
		System.out.println("Marvel DC Heroes with Webflux and AmazonDynamoDB");
	}

}
