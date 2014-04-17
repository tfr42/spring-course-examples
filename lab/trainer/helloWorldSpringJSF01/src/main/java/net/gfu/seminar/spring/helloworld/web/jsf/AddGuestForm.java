package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AddGuestForm implements Serializable {
	private static final long serialVersionUID = 1136068035817345380L;
	private String firstname;
	private String lastname;

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
		getService().addGuest(guest);
		return "success";
	}

	public void setService(GreetingService service) {
		this.service = service;
	}

	public GreetingService getService() {
		return service;
	}
}
