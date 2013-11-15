package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Component;

@Component("specialGuest")
public class SpecialGuest extends GuestImpl {

	@Override
	public String toString() {
		return "SpecialGuest [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + "]";
	}
	
	@Override
	public String getName() {
		return "special guest " + super.getName();
	}
	
}
