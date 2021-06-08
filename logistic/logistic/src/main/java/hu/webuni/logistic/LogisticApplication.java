package hu.webuni.logistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.logistic.service.InitDBService;

@SpringBootApplication
public class LogisticApplication implements CommandLineRunner{

	@Autowired
	InitDBService initDBService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LogisticApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDBService.clearDB();
		initDBService.createUsersIfNeeded();
		initDBService.insertTestData();
	}

}
