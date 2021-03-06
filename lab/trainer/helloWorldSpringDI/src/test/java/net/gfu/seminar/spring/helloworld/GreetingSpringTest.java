package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class GreetingSpringTest {

	@Autowired
	private GreetingService service;

	@Before
	public void setUp() {
	}

	@Test
	public void testWelcome() {
		assertNotNull(this.service);
		String welcome = this.service.welcome();
		assertTrue(welcome.contains("Rainer"));
		assertTrue(welcome.contains("Fall"));
	}

}
