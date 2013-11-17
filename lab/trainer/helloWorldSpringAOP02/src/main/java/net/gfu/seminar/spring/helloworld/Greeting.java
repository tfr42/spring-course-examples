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

	private List<Guest> guestList;

	public Greeting() {
		this.guestList = new ArrayList<Guest>();
	}
	
	public Greeting(Guest guest) {
		this();
		System.out.println("Constructor Greeting(Guest guest) with " + guest);
		this.setGuest(guest);
	}

	public Greeting(List<Guest> guests) {
		System.out.println("Constructor Greeting(List<Guest> guests) with " + guests);
		this.guestList = guests;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		String guestNames = this.guestList.toString();
		return String.format("Welcome %1$s to Spring!", guestNames);
	}

	/**
	 * Accessor method returning the internal state.
	 * 
	 * @return
	 */
	public Guest getGuest() {
		return guestList.get(guestList.size()-1);
	}

	/**
	 * Mutator method changing the internal state.
	 * 
	 * @param guest
	 */
	public void setGuest(Guest guest) {
		this.addGuest(guest);
	}

	public void addGuest(Guest guest) {
		this.guestList.add(guest);
	}

}
