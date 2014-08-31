package net.gfu.seminar.spring.helloworld.config;

import net.gfu.seminar.spring.helloworld.Greeting;
import net.gfu.seminar.spring.helloworld.Guest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = {"classpath:/guest.properties"} )
@Import(PersistenceConfig.class)
public class ApplicationConfig {

	@Value("${firstname}")
	private String firstname;
	@Value("${lastname}")
	private String lastname;

	@Bean
	public Greeting greeting() {
		return new Greeting(guest());
	}

	@Bean
	public Guest guest() {
//		return new Guest(firstname, lastname);
		return new Guest();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//		configurer.setLocation( new ClassPathResource("guest.properties"));
		return configurer;
	}
}