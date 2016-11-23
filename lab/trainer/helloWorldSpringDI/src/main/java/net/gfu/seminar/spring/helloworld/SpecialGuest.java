package net.gfu.seminar.spring.helloworld;

import java.util.Properties;

public class SpecialGuest extends GuestImpl {
	
	public SpecialGuest(Properties props) {
		this(props.getProperty("firstName"), props.getProperty("lastName"));
	}
	
	protected SpecialGuest(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public void init() {
		System.out.println("init called for "+this.toString());
	}

	@Override
	public String toString() {
		return "SpecialGuest [firstName=" + getFirstName()
				+ ", lastName=" + getLastName() + "]";
	}
	
}