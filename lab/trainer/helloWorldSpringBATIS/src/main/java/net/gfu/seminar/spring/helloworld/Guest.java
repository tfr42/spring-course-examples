package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

/**
 * An instance of <code>Guest</code> represents an entity of a guest.
 *  
 * @author tf
 *
 */
public class Guest {
	private Long id;
	private String firstName;
	private String lastName;
	
	public Guest() {
		this("","");
	}
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}