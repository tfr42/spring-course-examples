package net.gfu.seminar.spring.helloworld;


public interface GreetingService {

	String welcome();

	void addGuest(Guest guest);

	Guest findById(Long id);

}