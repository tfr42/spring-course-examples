package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/guest.properties")
@EnableAspectJAutoProxy
public class ApplicationConfig {

	@Value("${guest.firstname}")
	private String firstName;
	@Value("${guest.lastname}")
	private String lastName;

	@Bean
	public GreetingService greeting() {
		return new Greeting(guest());
	}

	@Bean
	public Guest guest() {
		return new GuestImpl(this.firstName, this.lastName);
	}

	@Bean
	public SimpleTraceAdvice traceAdvice() {
		return new SimpleTraceAdvice();
	}

	@Bean
	public ExecutionTimeAdvice executionTimeAdvice() {
		return new ExecutionTimeAdvice();
	}


	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		return p;
	}

}
