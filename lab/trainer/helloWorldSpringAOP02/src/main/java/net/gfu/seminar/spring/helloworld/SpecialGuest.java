package net.gfu.seminar.spring.helloworld;

import java.util.Properties;

public class SpecialGuest extends GuestImpl {
	
	public SpecialGuest(Properties props) {
		super(props.getProperty("firstName"), props.getProperty("lastName"));
	}
	
	public void init() {
		System.out.println("init called for "+this.toString());
	}

	@Override
	public String toString() {
		return "SpecialGuest [getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + "]";
	}
	
}