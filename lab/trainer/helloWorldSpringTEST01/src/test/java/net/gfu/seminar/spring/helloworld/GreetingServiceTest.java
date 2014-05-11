package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingServiceTest {

	private static final Logger LOG = Logger.getLogger(GreetingServiceTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOG.info("tearDownAfterClass");
	}
	
	private GreetingService greeting;
	private ConfigurableApplicationContext beanFactory;

	@Before
	public void setUp() throws Exception {
		LOG.info("setUp");
		beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.greeting =  beanFactory.getBean(GreetingService.class);
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
		beanFactory.close();
	}

	@Test
	public void testWelcome() {
		LOG.debug(greeting.welcome());
		assertNotNull(greeting.welcome());
	}

}
