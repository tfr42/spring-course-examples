package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Component;

@Component("specialGuest")
public class SpecialGuest extends GuestImpl {

	@Override
	public String toString() {
		return "SpecialGuest [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + "]";
	}

}
