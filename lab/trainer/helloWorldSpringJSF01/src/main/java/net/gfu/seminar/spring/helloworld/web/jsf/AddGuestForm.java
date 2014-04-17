package net.gfu.seminar.spring.helloworld.web.jsf;

import java.io.Serializable;

import javax.inject.Inject;

import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestImpl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("guest")
@Scope(value="request")
public class AddGuestForm implements Serializable {
	private static final long serialVersionUID = 1136068035817345380L;
	private String firstname;
	private String lastname;

	@Inject
	private GreetingService service;

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public String welcome() {
		Guest guest = new GuestImpl(this.getFirstname(), this.getLastname());
		this.service.addGuest(guest);
		return "success";
	}

}
