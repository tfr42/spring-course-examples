package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Says hello to a guest.
 * 
 * @author tf
 * 
 */
public class Greeting implements GreetingService {

	private Guest guest;

	public Greeting() {
	}
	
	@Autowired
	public Greeting( Guest guest) {
		this.setGuest(guest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		String guestNames = this.guest.getName();
		return String.format("Welcome %1$s to Spring!", guestNames);
	}

	/**
	 * Accessor method returning the internal state.
	 * 
	 * @return
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Mutator method changing the internal state.
	 * 
	 * @param guest
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

}
