package net.gfu.seminar.spring.helloworld.web;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class AddGuestForm implements Serializable {
	private static final long serialVersionUID = 2579066747202114544L;
	
	@Pattern(regexp="^[A-Z][a-z]+") private String firstname;
	@Pattern(regexp="^[A-Za-z\\s]+") private String lastname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "AddGuestForm [firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}
	
}
