package com.example.Home_User_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomeUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeUserServiceApplication.class, args);
	}

}
