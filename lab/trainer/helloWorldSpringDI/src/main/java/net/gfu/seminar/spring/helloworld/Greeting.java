package net.gfu.seminar.spring.helloworld;

import java.util.ArrayList;
import java.util.List;

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

	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

	/* (non-Javadoc)
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		return String.format("Welcome %1$s to Spring!", guest.getName());
	}

	@Override
	public void addGuest(Guest guest) {
		this.setGuest(guest);
	}

	@Override
	public List<Guest> findAll() {
		ArrayList<Guest> guests = new ArrayList<>();
		guests.add(this.getGuest());
		return guests;
	}

	@Override
	public Guest findById(Long id) {
		return this.getGuest();
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
