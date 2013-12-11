package net.gfu.seminar.spring.helloworld;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
 * Says hello to a guest.
 * 
 * @author tf
 * 
 */
@Service
public class Greeting {

	@Inject
	private Guest guest;

	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.guest);
	}

}
