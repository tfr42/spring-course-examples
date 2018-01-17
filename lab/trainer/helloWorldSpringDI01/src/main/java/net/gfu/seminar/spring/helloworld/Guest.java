package net.gfu.seminar.spring.helloworld;

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
		// using defaults for all properties
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

}