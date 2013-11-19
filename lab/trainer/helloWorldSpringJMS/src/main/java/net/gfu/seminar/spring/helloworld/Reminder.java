package net.gfu.seminar.spring.helloworld;

public class Reminder extends Greeting {

	public void sayHello() {
		System.out.println("Are you still there, "+ this.getGuest()+"?");
	}
}
