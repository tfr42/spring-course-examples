package net.gfu.seminar.jaxrs.helloworld;

import static org.junit.Assert.assertThat;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
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

	private static final Logger LOG = Logger.getLogger(HelloWorldRestClientIntegrationTest.class);
	private static final String uri = "http://localhost:48080/helloWorldSpringREST/";
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
		LOG.info("REST response= " + response);
		assertThat(response, CoreMatchers.containsString("Hello, world!"));
	}

	@Test
	public void testGetXmlResource() {
		String xmlResponse = resource.path("jaxrs").path("helloworld")
				.accept(MediaType.TEXT_XML).get(String.class);
		LOG.info("REST response= " + xmlResponse);
		assertThat(xmlResponse, CoreMatchers.containsString("Hello, world!"));
	}
	
	@Test
	public void testGetPlainResourceWithPathVariable() {
		String response = resource.path("rest").path("helloworld").path("Anna")
				.accept(MediaType.TEXT_PLAIN).get(String.class);
		LOG.info("REST response= " + response);
		assertThat(response, CoreMatchers.containsString("Hello, Anna!"));
	}
	
	@Test
	public void testGetJsonResourceWithPathVariable() {
		String response = resource.path("rest").path("helloworld").path("guest").path("Klara").path("Fall")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		LOG.info("REST response= " + response);
		assertThat(response, CoreMatchers.containsString("\"firstName\":\"Klara\""));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(uri).build();
	}
}
