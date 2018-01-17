package net.gfu.seminar.spring.helloworld;
/**
 *  Starts the HelloWorld application using plain Java.
 * 
 * @author tf
 *
 */
public class ApplicationWithPlainJava {

	public static void main(String[] args) {
		// XXX at runtime two objects are instantiated with new
		Guest guest = new GuestImpl("Hans", "Dampf");
		GreetingService reception = new Greeting(guest);
		System.out.println(reception.welcome());
	}

}
