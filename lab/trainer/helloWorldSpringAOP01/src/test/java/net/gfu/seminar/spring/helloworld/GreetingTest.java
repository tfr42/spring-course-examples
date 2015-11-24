package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/tracingAdvice.xml" })
public class GreetingTest {

	private static final Logger LOG = Logger.getLogger(GreetingTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOG.info("tearDownAfterClass");
	}
	
	@Autowired
	private GreetingService greeting;
	
	@Before
	public void setUp() throws Exception {
		LOG.info("setUp");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
	}

	@Test
	public void testWelcomeWithOneGuest() {
		String welcomeMessage = greeting.welcome();
		LOG.debug(welcomeMessage);
		assertTrue(welcomeMessage.contains("Hans"));
		assertTrue(welcomeMessage.contains("Dampf"));
	}
	
	@Test
	public void testSetGuest() {
		String welcomeMessage = greeting.welcome();
		LOG.debug(welcomeMessage);
		assertNotNull(welcomeMessage);
	}

}
