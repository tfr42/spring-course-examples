package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

public class GuestImpl implements Guest {

	private String firstName;
	private String lastName;
	private Long id;

	public GuestImpl() {
		this("", "");
	}

	public GuestImpl(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public GuestImpl(Long id, String firstName, String lastName) {
		this(firstName, lastName);
		this.id = id;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
	}

	@Override
	public String toString() {
		return "GuestImpl [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Long getId() {
		return id;
	}

}
