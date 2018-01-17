package net.gfu.seminar.spring.helloworld;

import java.util.List;

/**
 * Provides the welcome message.
 */
public interface GreetingService {

	String welcome();

	void addGuest(Guest guest);

	List<Guest> findAll();

	Guest findById(Long id);

}