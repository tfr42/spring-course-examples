package net.gfu.seminar.spring.helloworld.web;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddGuestForm implements Serializable {
	private static final long serialVersionUID = 2579066747202114544L;

	@Pattern(regexp="^[A-Z][a-z]+", message="Ungültiger Name")
	private String firstname;

	@Size(min = 2, max = 20, message="Die Länge muss zwischen 2 und 20 liegen")
	private String lastname;

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	@Override
	public String toString() {
		return "AddGuestForm [firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}
	
}
