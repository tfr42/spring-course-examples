package net.gfu.seminar.jaxrs.helloworld;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Integration test using Jersey Client API.
 * @author tf
 *
 */
public class HelloWorldRestClientIntegrationTest {

	private static final String uri = "http://localhost:8080/helloWorldSpringREST/";
	private WebResource resource;

	@Before
	public void before() {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		resource = client.resource(getBaseURI());
	}

	@Test
	public void testGetPlainResource() {
		String response = resource.path("jaxrs").path("helloworld")
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("REST response= " + response);
		assertThat(response, containsString("Hello, World!"));
	}

	@Test
	public void testGetXmlResource() {
		String xmlResponse = resource.path("jaxrs").path("helloworld")
				.accept(MediaType.TEXT_XML).get(String.class);
		System.out.println("REST response= " + xmlResponse);
		assertThat(xmlResponse, containsString("Hello, World!"));
	}
	
	@Test
	public void testGetPlainResourceWithPathVariable() {
		String response = resource.path("rest").path("helloworld").path("Anna")
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		System.out.println("REST response= " + response);
		assertThat(response, containsString("Hello, Anna!"));
	}
	
	@Test
	public void testGetJsonResourceWithPathVariable() {
		String response = resource.path("rest").path("helloworld").path("guest").path("Klara").path("Fall")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println("REST response= " + response);
		assertThat(response, containsString("\"firstName\":\"Klara\""));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(uri).build();
	}
}
