package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Says hello to a guest.
 * 
 * @author tf
 * 
 */
@Component("greeting")
public class Greeting {

	private Guest guest;

	public Greeting() {
	}

	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

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
	 * Dependency injected by Spring Container.
	 * Spring supports several annotations for injecting dependencies.
     *
	 * @param guest
	 */
	// TODO: Choose one of the following
	@Autowired // aus dem Spring Framework
//	@Resource  // aus javax.annotation.Resource (Java EE 5)
//	@Inject    // aus javax.inject.Inject aus CDI (Java EE 6)
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

}
