package net.gfu.seminar.spring.helloworld;

public class Goodmorning extends Greeting {
	
	public void wakeup() {
		System.out.println("Wake up " + this.getGuest());
	}

}
