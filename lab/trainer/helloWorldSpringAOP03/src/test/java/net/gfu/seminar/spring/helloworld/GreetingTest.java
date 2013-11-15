package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

public class GreetingTest {
	
	private GreetingService service;

	@Before
	public void setUp() {
		System.setProperty("firstname", "Hans");
		System.setProperty("lastname", "Dampf");
		GenericApplicationContext ctx = new GenericApplicationContext();
		new ClassPathBeanDefinitionScanner(ctx)
				.scan("net.gfu.seminar.spring.helloworld");
		ctx.refresh(); 
		this.service = ctx.getBean("greeting", GreetingService.class);
	}
	
	@Test
	public void testWelcome() {
		assertNotNull(this.service);
		String welcome = this.service.welcome();
		assertTrue(welcome.contains("Hans"));
		assertTrue(welcome.contains("Dampf"));
	}

}
