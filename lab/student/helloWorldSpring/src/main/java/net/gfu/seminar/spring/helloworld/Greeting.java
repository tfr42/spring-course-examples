package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
public class Greeting {
	
	private Guest guest;
	
	public Greeting() {
	}
	
	@Autowired
	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.getGuest());
	}
	
	/**
	 * Accessor method returning the internal state.
	 * @return
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Mutator method changing the internal state.
	 * @param guest
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	

}
