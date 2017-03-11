package net.gfu.seminar.spring.helloworld;

import org.springframework.util.Assert;

import java.util.Scanner;

public class GuestImpl implements Guest {

	private String firstName;
	private String lastName;

	public GuestImpl() {
		this("", "");
	}

	public GuestImpl(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
		scanner.close();
	}

	@Override
	public String toString() {
		return "GuestImpl [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void init() {
		System.out.println("init called on " + this.toString());
		Assert.hasText(this.getFirstName(), "Firstname is required");
		Assert.hasText(this.getLastName(), "Lastname is required");
	}

}