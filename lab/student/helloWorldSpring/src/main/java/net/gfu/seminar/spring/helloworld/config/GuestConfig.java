package net.gfu.seminar.spring.helloworld.config;

import net.gfu.seminar.spring.helloworld.Guest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestConfig {
	
	@Bean
	public Guest guest() {
		return new Guest("Hans", "Fall");
	}

	@Bean
	public Guest guest2() {
		return new Guest("Rainer", "Fall");
	}
}
