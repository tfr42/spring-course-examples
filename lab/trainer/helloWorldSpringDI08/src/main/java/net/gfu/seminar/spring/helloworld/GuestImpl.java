package net.gfu.seminar.spring.helloworld;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("guest")
public class GuestImpl implements Guest {
	@Value("Hans")
	private String firstName;
	@Value("Dampf")
	private String lastName;

	public GuestImpl() {
		this("","");
	}
	
	public GuestImpl(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
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
		return "GuestImpl [firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

}
