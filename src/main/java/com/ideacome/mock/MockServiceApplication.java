package com.ideacome.mock;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockServiceApplication {
	private static Logger logger = Logger.getLogger(MockServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MockServiceApplication.class, args);
		
		logger.info("service started");
	}
}
