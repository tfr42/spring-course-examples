package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.stereotype.Component;

@Component("guest")
@XmlRootElement
public class GuestImpl implements Guest, Serializable {
	private static final long serialVersionUID = -4700804809827556933L;
	private String firstName;
	private String lastName;
	private Long id;

	public GuestImpl() {
		this("", "");
	}

	public GuestImpl(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public GuestImpl(Long id, String firstName, String lastName) {
		this(firstName, lastName);
		this.id = id;
	}
	
	@XmlTransient
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
	}

	@Override
	public String toString() {
		return "GuestImpl [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

	@XmlElement
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement
	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement
	@Override
	public Long getId() {
		return id;
	}

}
