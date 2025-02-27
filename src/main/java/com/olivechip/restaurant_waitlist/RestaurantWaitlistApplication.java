package com.olivechip.restaurant_waitlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RestaurantWaitlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantWaitlistApplication.class, args);
	}
}