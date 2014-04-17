package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("guest")
@Entity @Table(name="guests")
@NamedQueries({
	@NamedQuery(name="forName", query="from GuestImpl as g where g.lastName like ?1"),
	})
public class GuestImpl implements Guest, Serializable  {
	
	private static final long serialVersionUID = 6697160661899899888L;
	@Value("#{'Hans'}")
	private String firstName;
	@Value("#{'Fall'}")
	private String lastName;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public GuestImpl() {
		this("","");
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

	@Override
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public void setName(String name) {
		Scanner scanner = new Scanner(name);
		this.firstName = scanner.next();
		this.lastName = scanner.next();
	}

	@Override
	public String toString() {
		return "GuestImpl [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
