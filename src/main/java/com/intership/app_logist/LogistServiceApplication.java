package com.intership.app_logist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication (exclude = {R2dbcAutoConfiguration.class})
//@EnableR2dbcRepositories(basePackages = "com.intership.app_logist.repository")
public class LogistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogistServiceApplication.class, args);
	}
}
