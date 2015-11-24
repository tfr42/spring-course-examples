package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;


public class VipGuest extends SpecialGuest {

	//@Value("#{systemProperties.pseudonym}")
	private String pseudonym;

	public VipGuest(@Value("#{nameProperties['firstname']}") String firstName, @Value("#{nameProperties['lastname']}") String lastName) {
		super(firstName, lastName);
	}

	@Override
	public String toString() {
		return "VipGuest [pseudonym=" + pseudonym + ", firstName()=" + getFirstName() + ", lastName()="
				+ getLastName() + "]";
	}

}
