package com.olivechip.restaurant_waitlist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.olivechip.restaurant_waitlist.config.H2TestConfig;

@SpringBootTest
@Import(H2TestConfig.class)
class RestaurantWaitlistApplicationTests {

	@Test
	void contextLoads() {
	}

}
