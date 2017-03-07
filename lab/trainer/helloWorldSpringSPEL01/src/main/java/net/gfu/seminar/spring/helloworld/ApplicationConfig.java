package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/guest.properties")
public class ApplicationConfig {

	@Autowired
	private Environment environment;

	@Bean
	public Guest guest(@Value("#{environment.firstName}") String firstName, @Value("#{environment.lastName}") String lastName)  {
		return new Guest(firstName,lastName);
	}

	@Bean
	public Guest specialGuest(@Value("#{environment.firstName?:'Elvis'}") String firstName, @Value("#{environment.lastName?:'Presley'}") String lastName)  {
		return new Guest(firstName,lastName);
	}
	
	@Bean
	public GreetingService greeting(@Value("#{systemProperties['isVip']?specialGuest:guest}") Guest guest) {
		return new Greeting(guest);
	}
}
