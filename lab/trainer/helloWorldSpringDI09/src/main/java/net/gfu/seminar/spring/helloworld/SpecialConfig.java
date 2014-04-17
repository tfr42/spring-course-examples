package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("special")
@PropertySource(value = { "classpath:/special.properties" })
public class SpecialConfig {

	@Bean  
	public Guest specialGuest(@Value("${firstName}") String firstName, @Value("${lastName}") String lastName) { 
		return new SpecialGuest( firstName, lastName);
	}
}
