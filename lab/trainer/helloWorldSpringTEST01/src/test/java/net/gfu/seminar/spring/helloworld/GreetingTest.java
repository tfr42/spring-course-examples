package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:testContext.xml" })
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
	
//	@Autowired 
//	@Qualifier("greeting")
//	@Resource(name="greeting")
	@Resource
	private GreetingService greeting;
	@Resource
	private GreetingService noGuestGreeting;
	
	@Autowired
	private Guest guest;

	@Before
	public void setUp() throws Exception {
		LOG.info("setUp");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
	}

//	@Test(timeout=1000)
	@Test
	@Repeat(100) @Timed(millis = 1000)
	public void testWelcome() {
		assertNotNull(greeting.welcome());
		LOG.debug(greeting.welcome());
	}
	
	@Test
	public void testGuest() {
	 	assertEquals("Hans Dampf" , guest.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
//	@ExpectedException(IllegalArgumentException.class)
	public void verifyThatWelcomeThrowsExceptionForNullGuest() {
		noGuestGreeting.welcome();
		//throw new IllegalArgumentException();
	}

}
