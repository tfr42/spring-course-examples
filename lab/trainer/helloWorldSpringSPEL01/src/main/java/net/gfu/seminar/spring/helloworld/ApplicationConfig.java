package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:/guest.properties", ignoreResourceNotFound = false)
public class ApplicationConfig {

	@Autowired
	private Environment environment;

	@Bean
	public Guest guest(@Value("#{environment.firstName}") String firstName, @Value("#{environment.lastName}") String lastName)  {
		return new Guest(firstName,lastName);
	}

	@Bean
	public Guest specialGuest(@Value("#{environment.firstName?:'Elvis'}") String firstName, @Value("#{environment.lastName?:'Presley'}") String lastName)  {
		return new SpecialGuest(firstName,lastName);
	}
	
	@Bean
	public Greeting greeting(@Value("#{environment.isVip?specialGuest:guest}") Guest aGuest) {
		return new Greeting(aGuest);
	}

}
