package net.gfu.seminar.spring.helloworld;

import java.util.Date;

public class BirthdayChild extends GuestImpl {
	
	private Date dayOfBirth;

	public BirthdayChild(String firstName, String lastName, Date dayOfBirth) {
		super(firstName, lastName);
		this.setBirthday(dayOfBirth);
	}

	protected void setBirthday(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	@Override
	public String toString() {
		return "BirthdayChild [dayOfBirth=" + dayOfBirth + ", getId()="
				+ getId() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + "]";
	}
	

}
