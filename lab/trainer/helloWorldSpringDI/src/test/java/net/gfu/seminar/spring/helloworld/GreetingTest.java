package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class GreetingTest {

	private GreetingService service;
	private AnnotationConfigApplicationContext ctx;

	@Before
	public void setUp() {
		System.setProperty("firstname", "Hans");
		System.setProperty("lastname", "Dampf");
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(ApplicationConfig.class);
		ctx.refresh();
		this.service = ctx.getBean("greeting", GreetingService.class);
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}

	@Test
	public void testWelcome() {
		assertNotNull(this.service);
		String welcome = this.service.welcome();
		assertTrue(welcome.contains("Rainer"));
		assertTrue(welcome.contains("Fall"));
	}

}