package net.gfu.seminar.springws;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import net.gfu.helloworld.types.HelloRequest;
import net.gfu.helloworld.types.HelloResponse;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Endpoint
public class HelloWorldEndpoint {
	private static final String NAMESPACE_URI = "http://helloworld.gfu.net/types";
	private static final Logger LOG = Logger
			.getLogger(HelloWorldEndpoint.class);

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloRequest")
	@ResponsePayload
	public HelloResponse hello(@RequestPayload HelloRequest request) {
		String name = request.getFirstname() + " " + request.getLastname();
		LOG.info("saying hello to "+ name);
		HelloResponse responseType = new HelloResponse();
		responseType.setReturn("Hello, " + name + "!");
		return responseType;
	}

//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloRequest")
//	@ResponsePayload
	public Element hello(@RequestPayload Element requestElement) {
		StringResult requestElementAsString = new StringResult();
		transform(new DOMSource(requestElement), requestElementAsString);
		LOG.debug("Request: " + requestElementAsString);
		String firstname = extractName(requestElement, "firstname");
		String lastname = extractName(requestElement, "lastname");
		Source responseElement = new StringSource(
				"<ns1:helloResponse xmlns:ns1='http://helloworld.gfu.net/types'>"
						+ "<return>Hello, " + firstname + " " + lastname+ "!</return>"
						+ "</ns1:helloResponse>");
		DOMResult outputTarget = new DOMResult();
		transform(responseElement, outputTarget);

		return (Element) outputTarget.getNode().getFirstChild();
	}

	private String extractName(Element requestElement, String elementName) {
		String name = null;
		NodeList nodeList = requestElement.getElementsByTagName(elementName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			name = ((Element)item).getTextContent();
		}
		return name;  
	}

	private void transform(Source responseElement, Result outputTarget) {
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(responseElement, outputTarget);
		} catch (TransformerConfigurationException e) {
			LOG.error(e.getMessage(),e);
		} catch (TransformerFactoryConfigurationError e) {
			LOG.error(e.getMessage(),e);
		} catch (TransformerException e) {
			LOG.error(e.getMessage(),e);
		}
	}

}
