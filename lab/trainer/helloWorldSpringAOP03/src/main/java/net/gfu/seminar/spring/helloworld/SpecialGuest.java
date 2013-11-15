package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Component;

//@Component
public class SpecialGuest extends GuestImpl {

	@Override
	public String toString() {
		return "SpecialGuest [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + "]";
	}
	
	public void init() {
		System.out.println("Init: " + this.toString());
	}
	
}
