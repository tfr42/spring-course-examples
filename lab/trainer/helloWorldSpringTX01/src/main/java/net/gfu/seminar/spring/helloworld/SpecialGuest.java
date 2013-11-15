package net.gfu.seminar.spring.helloworld;


public class SpecialGuest extends GuestImpl {

	@Override
	public String toString() {
		return "SpecialGuest [getClass()=" + getClass() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
