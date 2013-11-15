package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath:/tracingAdvice.xml", "classpath:/persistenceLayer.xml" })
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
	public void testWelcome() {
		String welcome = greeting.welcome();
		assertNotNull(welcome);
		LOG.debug(welcome);
	}

	@Test
	public void testGetGuest() {
		assertNotNull(greeting.findAll());
		//assertEquals("Hans Dampf", greeting.getGuest().getName());
	}
	
	@Test public void testCreateNewGuest() {
		this.greeting.addGuest(new GuestImpl("Hans", "Dampf"));
	}
	
	@Test public void findAll() { 
		List<Guest> all = this.greeting.findAll();
	}

	@Test public void findByName() {
		String firstname = null;
		String lastname = null;
		Guest guest = this.greeting.findByName(firstname, lastname);
	}

	@Test public void findById() {
		Long id = null;
		Guest guest = this.greeting.findById(id);
		
	}

}
