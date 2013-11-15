package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;
public class GuestImpl implements Guest {
	
	private String firstName;
	private String lastName;
	
	private Long id;
	
	public GuestImpl() {
		this("","");
	}
	
	public GuestImpl(String firstName, String lastName) {
		this(null, firstName,lastName);
	}
	
	public GuestImpl(Long id, String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return "GuestImpl [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
