package net.gfu.seminar.spring.integration;

import org.apache.log4j.Logger;

public class HelloWorldMessageSubscriber {
	private static final Logger LOG = Logger.getLogger(HelloWorldMessageSubscriber.class);

	public String sayHello(String s) {
		String message = "Hello, " + s + "!";
		LOG.debug("saying:" + message);
		return message;
	}
}
