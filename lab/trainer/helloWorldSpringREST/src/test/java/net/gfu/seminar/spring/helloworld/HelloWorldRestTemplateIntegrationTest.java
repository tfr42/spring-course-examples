package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class HelloWorldRestTemplateIntegrationTest {
	@Autowired
	RestOperations restTemplate;

	@Test
	public void testGetResourceHelloWorld() {
		String response = restTemplate.getForObject(
				"http://localhost:8080/helloWorldSpringREST/rest/helloworld",
				String.class);
		assertEquals("Hello, world!", response);
	}
}
