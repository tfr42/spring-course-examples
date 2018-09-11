package net.gfu.seminar.spring.helloworld;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * JUnit 5 Test with JUnitPlatform class which is a JUnit4-based Runner.
 * 
 * @author tf
 *
 */
@RunWith(JUnitPlatform.class)
public class GreetingsTest {
	
	private GreetingService greetings = new Greeting(new GuestImpl("Hans", "Dampf"));
	
    @Test
    void whenCallingWelcome_thenReturnGreetingMessage() {
        assertTrue("Welcome Hans Dampf to Spring!".equals(greetings.welcome()));
    }
}