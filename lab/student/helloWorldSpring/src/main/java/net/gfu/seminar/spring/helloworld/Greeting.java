package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
public class Greeting implements ApplicationContextAware, GreetingService {
	
	private Guest guest;
	
	public Greeting() {
	}
	
	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

	@Override
    public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.getGuest());
	}

    @Override
    public void addGuest( Guest guest ) {
      this.setGuest(guest);
    }

    /**
	 * Accessor method returning the internal state.
	 * @return
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Mutator method changing the internal state.
	 * @param guest
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public void init() {
		System.out.println("init was called");
	}
	
	public void close() {
		System.out.println("close was called");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println(applicationContext.toString());
        System.out.println("Environment:" + applicationContext.getEnvironment().toString());
		
	}
	

}
