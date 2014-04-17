package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Service;

/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
@Service
public class Greeting implements GreetingService {
	
	private GuestDao dao;
	
	public Greeting(GuestDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.dao.findAll());
	}

}
