package it.atac.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "it.atac.project")
@EnableMongoRepositories(basePackages = "it.atac.project.mongo.repository")
@EntityScan(basePackages = "it.atac.project.mongo.document")
public class RealtimeDataCommunicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeDataCommunicationApplication.class, args);

	}

}
