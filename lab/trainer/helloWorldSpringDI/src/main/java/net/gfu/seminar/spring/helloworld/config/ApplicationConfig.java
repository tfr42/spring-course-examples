package net.gfu.seminar.spring.helloworld.config;

import net.gfu.seminar.spring.helloworld.aop.ExecutionTimeAdvice;
import net.gfu.seminar.spring.helloworld.Greeting;
import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestImpl;
import net.gfu.seminar.spring.helloworld.aop.SimpleTraceAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:/guest.properties" })
public class ApplicationConfig {

	@Bean
	public GreetingService greeting(Guest guest) {
		return new Greeting(guest);
	}

	@Bean(initMethod = "init")
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

}
