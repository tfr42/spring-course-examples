package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(SpecialConfig.class)
@PropertySource(value = { "classpath:/guest.properties" })
public class ApplicationConfig {

	@Value("${firstName}")
	private String firstName;
	@Value("${lastName}")
	private String lastName;
	
	@Autowired 
	@Qualifier("specialGuest") // TODO: comment and see what happens
	private Guest guest;

	@Bean
	public GreetingService greeting() {
		return new Greeting(guest);
	}

	@Bean
	public Guest guest() {
		return new GuestImpl(this.firstName, this.lastName);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		return p;
	}

}
