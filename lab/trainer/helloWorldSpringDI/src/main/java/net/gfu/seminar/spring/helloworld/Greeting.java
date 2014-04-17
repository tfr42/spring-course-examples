package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
@Service
public class Greeting implements GreetingService {
	
	private Guest guest;
	private GuestDao dao;
	
	public Greeting() {
	}
	
	public Greeting( Guest guest) {
		this.setGuest(guest);
	}
	
	public Greeting(GuestDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		String guestNames;
		if (dao != null) {
			List<Guest> all = this.dao.findAll();
			guestNames = all.toString();
		} else {
			guestNames = this.guest.getName();
		}

		return String.format("Welcome %1$s to Spring!", guestNames);
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
	
	@Override
	public void addGuest(Guest guest) {
		this.dao.create(guest);
	}

	@Override
	public Guest findById(Long id) {
		return dao.findById(id);
	}

}
