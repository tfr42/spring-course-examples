package net.gfu.seminar.spring.helloworld.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/restContext.xml" })
public class GreetingRestIntegrationTest {
	@Autowired
	RestOperations restTemplate;

	@Test
	public void testGetGreetingWithId()
			throws TransformerFactoryConfigurationError, TransformerException {
		Source result = restTemplate
				.getForObject(
						"http://localhost:48080/helloWorldSpringMVC01/greeting/{id}",
						Source.class, "1");
		assertNotNull(result);
		String resultAsString = transformXmlToString(result);
		System.out.println("Result: " + resultAsString);
		assertThat(resultAsString, containsString("Anna"));
	}

	private String transformXmlToString(Source inputSource)
			throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		StringWriter writer = new StringWriter();
		Result outputTarget = new StreamResult(writer);
		transformer.transform(inputSource, outputTarget);
		return writer.toString();
	}
}
