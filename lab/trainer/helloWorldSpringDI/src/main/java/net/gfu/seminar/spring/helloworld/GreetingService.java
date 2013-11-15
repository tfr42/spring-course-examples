package net.gfu.seminar.spring.helloworld;


public interface GreetingService {

	public String welcome();

	public void addGuest(Guest guest);

	public Guest findById(Long id);

}