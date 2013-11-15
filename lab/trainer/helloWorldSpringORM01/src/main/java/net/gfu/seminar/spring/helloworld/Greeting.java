package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
@Component("welcome")
public class Greeting implements GreetingService {
	
	private Guest guest;
	
	public Greeting() {
	}
	
	@Autowired
	public Greeting(@Qualifier("birthdayChild") Guest guest) {
		this.setGuest(guest);
	}

	/* (non-Javadoc)
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		//throw new UnsupportedOperationException("Not implemented");
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
