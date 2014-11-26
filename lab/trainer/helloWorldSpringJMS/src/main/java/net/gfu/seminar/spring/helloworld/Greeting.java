package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Says hello to a guest.
 * 
 * @author tf
 * 
 */
@Component
public class Greeting implements GreetingService {

	private Guest guest;
	protected GuestDao dao;

	public Greeting() {
	}

	@Autowired(required = true)
	public Greeting(@Qualifier("guest") Guest guest) {
		this.setGuest(guest);
	}

	public Greeting(List<Guest> guests) {

	}

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
		String guestNames;
		if (dao != null) {
			List<Guest> all = this.dao.findAll();
			guestNames = all.toString();
		} else {
			guestNames = this.guest.getName();
		}

		return String.format("Welcome %1$s to Spring!", guestNames);
	}


	public void init() {
		System.out.println("init was called");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest findById(Long id) {
		return dao.findById(id);
	}

}
