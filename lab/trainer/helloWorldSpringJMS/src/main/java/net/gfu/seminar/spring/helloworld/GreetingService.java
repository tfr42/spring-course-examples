package net.gfu.seminar.spring.helloworld;

import java.util.List;

public interface GreetingService {

	public String welcome();

	public void addGuest(Guest guest);

	public List<Guest> findAll();

	public Guest findByName(String firstname, String lastname);

	public Guest findById(Long id);

}