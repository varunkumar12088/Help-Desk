package com.learning;

import com.learning.properties.AiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties({AiProperties.class})
@EnableMongoRepositories
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

}
