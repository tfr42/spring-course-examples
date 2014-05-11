package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.Test;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class HelloWorldRestTemplateIntegrationTest {
		RestOperations restTemplate = new RestTemplate();

		@Test
		public void testGetGreetingWithId()
				throws TransformerFactoryConfigurationError, TransformerException {
			String firstname = "Rainer";
			String lastname = "Zufall";
			Guest guest = restTemplate
					.getForObject(
							"http://localhost:8080/helloWorldSpringREST/rest/helloworld/guest/{firstname}/{lastname}",
							Guest.class, firstname, lastname );
			System.out.println("Result: " + guest);
			assertNotNull(guest);
			assertEquals(guest.getFirstName(), firstname);
			assertEquals(guest.getLastName(), lastname);
		}
}