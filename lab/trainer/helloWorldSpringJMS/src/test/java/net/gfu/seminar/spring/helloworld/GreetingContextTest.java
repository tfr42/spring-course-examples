package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingContextTest {

	private static final Logger LOG = Logger.getLogger(GreetingContextTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOG.info("tearDownAfterClass");
	}
	
	private Greeting greeting;
	private ConfigurableApplicationContext beanFactory;

	@Before
	public void setUp() throws Exception {
		LOG.info("setUp");
		// / XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the
		// BeanFactory
		//Resource resource = new ClassPathResource("applicationContext.xml");
		beanFactory = new ClassPathXmlApplicationContext("testContext.xml");
		this.greeting = (Greeting) beanFactory.getBean("welcome");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
		beanFactory.close();
	}

	@Test
	public void testWelcome() {
		assertNotNull(greeting.welcome());
		LOG.debug(greeting.welcome());
	}

	@Test
	public void testGetGuest() {
		assertNotNull(greeting.getGuest());
		assertEquals("Rainer Unsinn", greeting.getGuest().getName());
		LOG.debug(greeting.getGuest());
	}

}
