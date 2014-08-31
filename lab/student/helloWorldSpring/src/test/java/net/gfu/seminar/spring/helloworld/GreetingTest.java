package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles("dev")
public class GreetingTest {
	// Spring
	// @Autowired
	// JSR-221
	// @Resource
	// CDI Java EE 6/7 JSR 330
	@Inject
	private Greeting greeting;

	@Test
	public void testWelcome() {
		assertNotNull(greeting.welcome());
	}

}
