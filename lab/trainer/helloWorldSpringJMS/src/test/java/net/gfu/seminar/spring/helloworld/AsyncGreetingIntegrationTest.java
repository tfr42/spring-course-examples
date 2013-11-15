package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Asynchronous test.
 * 
 * @author tf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/jms-sender.xml", "classpath:/jms-config.xml", "classpath:/testContext.xml" })
public class AsyncGreetingIntegrationTest {

	private static final Logger LOG = Logger.getLogger(AsyncGreetingIntegrationTest.class);

	@Autowired @Qualifier("sender")
	private GreetingService greeting;

	@Before public void setUp() {
		LOG.info("setUp");
	}
	
	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
	}

	@Test
	public void testWelcome() {
		String welcome = greeting.welcome();
		assertNotNull(welcome);
		LOG.debug(welcome);
	}

}
