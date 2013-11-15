package net.gfu.seminar.spring.helloworld;


public class SpecialGuest extends GuestImpl {

	public SpecialGuest() {
	}
	
	public SpecialGuest(String firstName, String lastName) {
		super(firstName,lastName);
	}

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
