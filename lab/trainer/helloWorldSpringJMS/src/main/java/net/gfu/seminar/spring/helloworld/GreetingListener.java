package net.gfu.seminar.spring.helloworld;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class GreetingListener implements MessageListener {
	private static final Logger LOG = Logger.getLogger(GreetingListener.class);

	@Autowired
	private GreetingMailer mailService;

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				String text = ((TextMessage) message).getText();
				LOG.info("Received message with text:" + text);

			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else if (message instanceof ObjectMessage) {
			if (message instanceof SpecialGuest) {
				SpecialGuest guest = (SpecialGuest) message;
				mailService.sendMessage(guest);
			}
		} else {
			throw new IllegalArgumentException(
					"Message must be of type TextMessage");
		}
	}

}
