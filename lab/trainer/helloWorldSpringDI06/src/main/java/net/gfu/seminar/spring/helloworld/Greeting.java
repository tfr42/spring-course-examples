package net.gfu.seminar.spring.helloworld;


/**
 * Says hello to a {@link Guest}.
 * 
 * @author tf
 *
 */
public class Greeting {
	
	private Guest guest;
	
	public Greeting() {
		System.out.println("default constructor");
	}
	
	public Greeting(Guest guest) {
		System.out.println("Constructor public Greeting(Guest guest) "+ guest);
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
		System.out.println("public void setGuest(Guest guest)" + guest);
		this.guest = guest;
	}

}
