package net.gfu.seminar.spring.helloworld;

import java.util.List;

/**
 * Says hello to a guest.
 * 
 * @author tf
 * 
 */
public class Greeting implements GreetingService {

	private GuestDao dao;

	public Greeting(GuestDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		List<Guest> all = this.dao.findAll();
		String guestNames = all.toString();
		return String.format("Welcome %1$s to Spring!", guestNames);
	}

	/**
	 * Accessor method returning the internal state.
	 * 
	 * @return
	 */
	public Guest getGuest() {
		List<Guest> all = this.dao.findAll();
		return all.get(all.size()-1);
	}

	/**
	 * Mutator method changing the internal state.
	 * 
	 * @param guest
	 */
	public void setGuest(Guest guest) {
		this.addGuest(guest);
	}

	@Override
	public void addGuest(Guest guest) {
		this.dao.create(guest);
	}
	
	@Override
	public List<Guest> findAll() {
		return dao.findAll();
	}

	@Override
	public Guest findByName(String firstname, String lastname) {
		List<Guest> list = dao.findByName(lastname);
		if (list.size() > 0) 
			return list.get(0);
		return null;
	}

	@Override
	public Guest findById(Long id) {
		return dao.findById(id);
	}

}
