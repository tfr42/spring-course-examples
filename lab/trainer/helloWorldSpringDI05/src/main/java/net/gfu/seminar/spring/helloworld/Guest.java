package net.gfu.seminar.spring.helloworld;

import org.springframework.util.Assert;

import java.util.Scanner;

/**
 * A guest.
 *
 * @author tf
 *
 */
public class Guest {

	private String firstName;

	private String lastName;

	public Guest() {
		this("", "");
	}

	/**
	 * Creates a new {@link Guest} with the given first- and lastname.
	 *
	 * @param firstName
	 * @param lastName
	 */
	public Guest(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
		scanner.close();
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
		return "Guest [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public void init() {
		System.out.println("init called on " + this.toString());
		Assert.hasText(this.getFirstName(), "Firstname is required");
		Assert.hasText(this.getLastName(), "Lastname is required");
	}

}