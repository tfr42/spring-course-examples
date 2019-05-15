package net.gfu.seminar.spring.helloworld.groovy;


/**
 * Says hello to a {@link Guest}.
 * 
 * @author tf
 *
 */
public class Greeting {

	private Guest guest;

	public Greeting() {
	}
	
	/**
	 * Creates a {@link Greeting} for the given {@link Guest}
	 * @param guest
	 */
	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

	/**
	 * Returns the welcome message.
	 * 
	 * @return the welcome message
	 */
	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.getGuest());
	}

	/**
	 * Accessor method returning the internal state.
	 * @return the guest
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Mutator method changing the internal state.
	 * @param guest the guest
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

}