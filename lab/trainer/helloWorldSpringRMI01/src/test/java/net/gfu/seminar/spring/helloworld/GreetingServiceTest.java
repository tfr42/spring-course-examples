package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/rmi-service.xml", "classpath:/rmi-client.xml","classpath:/applicationContext.xml"})
public class GreetingServiceTest {
	
	@Autowired @Qualifier("rmiGreetingServiceProxy")
	private GreetingService service;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWelcome() {
		String welcome = service.welcome();
		assertNotNull(welcome);
		System.out.println(welcome);
	}

}
