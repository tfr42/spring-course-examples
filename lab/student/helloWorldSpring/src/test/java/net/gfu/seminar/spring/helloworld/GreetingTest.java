package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class})
public class GreetingTest {
	
	@Inject
	private Greeting greeting;
	
	@Test
	public void testWelcome() {
		assertNotNull(greeting.welcome());
	}
	

}
