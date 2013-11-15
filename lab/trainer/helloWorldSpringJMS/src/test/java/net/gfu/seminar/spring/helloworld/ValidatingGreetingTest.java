package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/validation.xml" })
public class ValidatingGreetingTest {

	@Autowired
	private GreetingService greeting;
	
	@Test
	public void testValidationWithCorrectEmailAddress() {
		SpecialGuest guest = new SpecialGuest("Hans", "Fall","hfall@floor.com");
		greeting.addGuest(guest);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidationWithIncorrectEmailAddress() {
		SpecialGuest guest = new SpecialGuest("Rainer", "Fall","foo42");
		greeting.addGuest(guest);
	}

}
