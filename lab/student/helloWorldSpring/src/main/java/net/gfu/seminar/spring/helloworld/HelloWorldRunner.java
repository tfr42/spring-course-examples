package net.gfu.seminar.spring.helloworld;

/**
 * HelloWorld runner using plain Java.
 * 
 * @author tf
 * 
 */
public class HelloWorldRunner {

	public static void main(String[] args) {
		Guest guest = new Guest("Torsten", "Friebe");
		Greeting greeting = new Greeting(guest);
		System.out.println(greeting.welcome());
	}

}
