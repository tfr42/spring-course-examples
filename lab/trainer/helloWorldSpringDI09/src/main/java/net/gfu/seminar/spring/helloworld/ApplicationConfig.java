package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = { "classpath:/guest.properties" })
public class ApplicationConfig {

	@Value("${firstName}")
	private String firstName;
	@Value("${lastName}")
	private String lastName;

	@Bean
	public Greeting greeting() {
		return new Greeting(guest());
	}

	@Bean(initMethod = "init")
	public Guest guest() {
		return new Guest(this.firstName, this.lastName);
	}
	
	@Bean
	public Guest specialGuest() {
		return new SpecialGuest(this.firstName, this.lastName);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		return p;
	}

}
