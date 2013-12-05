package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/guest.properties")
@Import(PersistenceConfig.class)
public class ApplicationConfig {

	@Value("${guest.firstname}")
	private String firstName;
	@Value("${guest.lastname}")
	private String lastName;
	
	@Autowired
	GuestDao guestDao;

	@Bean
	public GreetingService greeting() {
		return new Greeting(guestDao);
	}

	@Bean
	public Guest guest() {
		return new GuestImpl(this.firstName, this.lastName);
	}

}
