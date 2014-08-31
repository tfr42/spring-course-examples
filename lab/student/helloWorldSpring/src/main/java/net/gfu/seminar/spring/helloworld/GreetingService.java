package net.gfu.seminar.spring.helloworld;

/**
 * Service interface of the Greeting service.
 */
public interface GreetingService {
    String welcome();
    void addGuest(Guest guest);
}
