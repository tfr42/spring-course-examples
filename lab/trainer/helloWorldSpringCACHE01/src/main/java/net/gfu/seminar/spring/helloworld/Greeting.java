package net.gfu.seminar.spring.helloworld;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;


/**
 * Says hello to a guest.
 * 
 * @author tf
 *
 */
@Service
public class Greeting implements GreetingService, BeanPostProcessor {
	
	private static final Logger LOG = Logger.getLogger(Greeting.class);
	
	private Guest guest;
	
	public Greeting() {
	}
	
	public Greeting(Guest guest) {
		this.setGuest(guest);
	}

	/* (non-Javadoc)
	 * @see net.gfu.seminar.spring.helloworld.GreetingService#welcome()
	 */
	@Override
	public String welcome() {
		return String.format("Welcome %1$s to Spring!", this.getGuest());
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
	
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		LOG.debug("postProcessAfterInitialization called for : " + arg0 + " with " +arg1);
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		LOG.debug("postProcessBeforeInitialization called for : "+ arg0 + " with " +arg1);
		return arg0;
	}
	

}
