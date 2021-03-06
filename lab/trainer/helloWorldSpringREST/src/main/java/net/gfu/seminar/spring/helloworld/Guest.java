package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Guest {

	private String firstName;
	private String lastName;
	
	public Guest() {
	}

	public Guest(String firstName, String lastName) {
		this();
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

}