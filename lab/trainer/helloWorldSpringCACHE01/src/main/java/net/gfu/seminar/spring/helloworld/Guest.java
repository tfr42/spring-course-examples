package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Guest implements Serializable {

	private static final long serialVersionUID = 7550209738527043417L;
	private static final Logger LOG = Logger.getLogger(Guest.class);

	private Long id;
	private String firstName;
	private String lastName;

	public Guest() {
		this("", "");
	}

	public Guest(String firstName, String lastName) {
		this(null, firstName, lastName);
	}

	public Guest(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		LOG.trace("constructor called");
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		LOG.trace("setFirstName called:" + firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		LOG.trace("setLastName called:" + lastName);
	}

	@Override
	public String toString() {
		return "GuestImpl [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	public void init() {
		LOG.debug("init called for : " + this.toString());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
