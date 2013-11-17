package net.gfu.seminar.spring.helloworld;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("birthdayChild")
public class BirthdayChild extends GuestImpl {
    //@Value("#{T(java.util.Calendar).getInstance().getTime()}")
	@Value("#{new java.text.SimpleDateFormat('dd.MM.yyyy').parse('11.11.2011')}")
	private Date dayOfBirth;

	public BirthdayChild() {
		// TODO Auto-generated constructor stub
	}

	public BirthdayChild(String firstName, String lastName, Date dayOfBirth) {
		super(firstName, lastName);
		this.dayOfBirth = dayOfBirth;
	}

	@Override
	public String toString() {
		return "BirthdayChild [dayOfBirth=" + dayOfBirth + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + "]";
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

}
