package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class GreetingTest {
	private static final Logger LOG = Logger.getLogger(GreetingTest.class);

	/**
	 * Test fixture injected by Spring Container.
	 * Spring supports several annotations for injecting dependencies.
	 */
	@Autowired // aus dem Spring Framework
//	@Resource  // aus javax.annotation.Resource (Java EE 5)
//	@Inject    // aus javax.inject.Inject aus CDI (Java EE 6)
	private GreetingService greeting;
	
	@Autowired MethodCallCounterAdvice advice;

	@Test
	@Repeat(23)
	//@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
	public void testWelcome() {
		String message = greeting.welcome();
		assertNotNull(message);
		LOG.debug(message);
	}
	
	@Test
	public void testWelcomeMethodCallingTreeTimes() {
		for (int i=0;i<3;i++) {
			greeting.welcome();
		}
		assertEquals(3, getCounterForWelcomeMethod().intValue());
	}
	
	private Integer getCounterForWelcomeMethod() {
		Map<String, Integer> counter = this.advice.getCounter();
		return counter.get("public abstract java.lang.String net.gfu.seminar.spring.helloworld.GreetingService.welcome()");
	}
}