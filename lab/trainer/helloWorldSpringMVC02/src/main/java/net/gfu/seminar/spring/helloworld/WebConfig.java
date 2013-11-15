package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class WebConfig {
	private @Autowired
	GuestDao guestDao;

	@Bean
	public GreetingService greetingService() {
		return new Greeting(guestDao);
	}
}
