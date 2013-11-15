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
@ContextConfiguration(classes = { ApplicationConfig.class,
		PersistenceConfig.class })
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
		assertNotNull(greeting.welcome());
		LOG.debug(greeting.welcome());
	}

	@Test
	public void testCreateNewGuest() {
		this.greeting.addGuest(new GuestImpl("Hans", "Dampf"));
	}

	@Test
	public void findAll() {
		List<Guest> all = this.greeting.findAll();
		assertNotNull(all);
	}

	@Test
	public void findByName() {
		String firstname = "Hans";
		String lastname = "Dampf";
		Guest guest = this.greeting.findByName(firstname, lastname);
		assertNotNull(guest);
	}

	@Test
	public void findById() {
		Long id = 1l;
		Guest guest = this.greeting.findById(id);
		assertNotNull(guest);
	}

}