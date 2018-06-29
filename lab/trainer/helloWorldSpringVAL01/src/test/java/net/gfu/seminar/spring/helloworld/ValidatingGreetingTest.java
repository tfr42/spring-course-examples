package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/validation.xml" })
public class ValidatingGreetingTest {

	@Autowired
	private GreetingService greeting;
	
	@Test
	public void testValidationWithCorrectEmailAddress() {
		SpecialGuest guest = new SpecialGuest("Hans", "Fall","hfall@floor.com");
		greeting.addGuest(guest);
		assertThat(greeting.welcome(), allOf(containsString("Hans"), containsString("Fall")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidationWithIncorrectEmailAddress() {
		SpecialGuest guest = new SpecialGuest("Rainer", "Fall","foo42");
		greeting.addGuest(guest);
	}

}
