package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class ApplicationConfig {

	@Value("Hans")
	private String firstName;
	@Value("Dampf")
	private String lastName;

	@Bean
	public GreetingService greeting() {
		return new Greeting(guest());
	}

	@Bean
	public Guest guest() {
		return new GuestImpl(this.firstName, this.lastName);
	}
	
}
