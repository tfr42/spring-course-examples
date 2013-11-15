package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
public class GuestImpl implements Guest, BeanPostProcessor, Serializable {
	
	private static final long serialVersionUID = 7550209738527043417L;

	private static final Logger LOG = Logger.getLogger(GuestImpl.class);
	
	private String firstName;
	private String lastName;
	
	public GuestImpl() {
		this("","");
	}
	
	public GuestImpl(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		LOG.trace("constructor called");
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
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		LOG.trace("setFirstName called:" + firstName);
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
		LOG.trace("setLastName called:" + lastName);
	}

	@Override
	public String toString() {
		return "GuestImpl [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	
	public void init() {
		LOG.debug("init called for : " +this.toString());
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		LOG.debug("postProcessAfterInitialization called for : " + arg0 + " with " +arg1);
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		LOG.debug("postProcessBeforeInitialization called for : "+ arg0 + " with " +arg1);
		return arg0;
	}

}
