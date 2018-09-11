package net.gfu.seminar.spring.helloworld;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * JUnit 5 Test using the SpringExtension class from Spring TestContext Framework.
 * 
 * @author tf
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class GreetingsJUnit5Test {
	
	@Autowired
	private GreetingService greetings;
	
    @Test
    void whenCallingWelcome_thenReturnGreetingMessage() {
        assertTrue("Welcome Hans Dampf to Spring!".equals(greetings.welcome()));
    }
}
