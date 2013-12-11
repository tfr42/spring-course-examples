package net.gfu.seminar.spring.helloworld.config;

import net.gfu.seminar.spring.helloworld.Greeting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GuestConfig.class)
public class ApplicationConfig {

	@Bean
	public Greeting welcome() {
		Greeting greeting = new Greeting();
		return greeting;
	}


}
