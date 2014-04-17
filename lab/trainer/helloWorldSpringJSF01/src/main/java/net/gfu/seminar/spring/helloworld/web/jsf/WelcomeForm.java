package net.gfu.seminar.spring.helloworld.web.jsf;

import java.io.Serializable;

import javax.inject.Inject;

import net.gfu.seminar.spring.helloworld.GreetingService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("welcome")
@Scope("request")
public class WelcomeForm implements Serializable {
	private static final long serialVersionUID = -1110440082884123464L;

	@Inject
	private GreetingService service;

	public String getMessage() {
		System.out.println("returning welcome message from service");
		String message = this.service.welcome();
		return message;
	}

}