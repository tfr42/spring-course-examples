package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

@Configuration
@ImportResource("classpath:/persistenceLayer.xml")
public class ApplicationConfig {
	@Bean
	public GreetingService welcome() {
		return new Greeting(guest());
	}

	@Bean
	@Scope(value="prototype")
	public Guest guest() {
		return new GuestImpl("Rainer", "Fall");
	}

}
