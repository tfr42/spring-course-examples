package net.gfu.seminar.spring.helloworld;
/**
 * HelloWorld runner using plain Java.
 * 
 * @author tf
 *
 */
public class HelloWorldRunner {

	public static void main(String[] args) {
		Guest guest = new GuestImpl("Hans", "Dampf");
		GreetingService reception = new Greeting(guest);
		System.out.println(reception.welcome());
	}

}
