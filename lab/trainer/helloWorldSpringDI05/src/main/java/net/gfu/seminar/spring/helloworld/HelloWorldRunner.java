package net.gfu.seminar.spring.helloworld;
/**
 * HelloWorld runner using plain Java.
 * 
 * @author tf
 *
 */
public class HelloWorldRunner {

	public static void main(String[] args) {
		GuestImpl guest = new GuestImpl("Torsten", "Friebe");
		GreetingService reception = new Greeting(guest);
		System.out.println(reception.welcome());
	}

}
