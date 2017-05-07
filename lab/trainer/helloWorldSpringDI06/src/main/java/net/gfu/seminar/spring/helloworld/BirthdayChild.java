package net.gfu.seminar.spring.helloworld;

import java.util.Date;

public class BirthdayChild extends Guest {
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
		return "BirthdayChild [dayOfBirth=" + dayOfBirth + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + "]";
	}
	
	
}