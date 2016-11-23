package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({ "classpath:/guest.properties" })
public class ApplicationConfig {

	@Bean
	public GreetingService greeting(Guest guest) {
		return new Greeting(guest);
	}

	@Bean
	public Guest guest(@Value("${guest.firstname}") String firstName, @Value("${guest.lastname}") String lastName) {
		return new GuestImpl(firstName, lastName);
	}

	@Bean
	public SimpleTraceAdvice traceAdvice() {
		return new SimpleTraceAdvice();
	}

	@Bean
	public ExecutionTimeAdvice executionTimeAdvice() {
		return new ExecutionTimeAdvice();
	}

	/**
	 * This static bean is only required in case the @Value annotation is used on fields of a @Configuration
	 * annotated class.
	 *
	 * @see http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html
     */
	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		// This replaces the @PropertySource annotation
		// Resource[] resourceLocations = new Resource[] {
		// new ClassPathResource("guest.properties"),
		// };
		// p.setLocations(resourceLocations);
		return p;
	}

}
