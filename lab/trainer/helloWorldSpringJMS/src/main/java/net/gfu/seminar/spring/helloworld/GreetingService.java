package net.gfu.seminar.spring.helloworld;

import java.util.List;

public interface GreetingService {

    String welcome();

	void addGuest(Guest guest);

	List<Guest> findAll();

	Guest findByName(String firstname, String lastname);

	Guest findById(Long id);

}