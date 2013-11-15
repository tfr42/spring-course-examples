package net.gfu.seminar.spring.integration;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.core.SubscribableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author tf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/si-config.xml" })
public class HelloWorldTest {

	@Resource(name = "input")
	private MessageChannel input;
	
	@Resource(name = "output")
	private PollableChannel output;
	
	@Resource(name = "gateway") // -> jms
	private Gateway gateway;
	
	@Resource(name = "jmsOut") 
	private SubscribableChannel jmsChannel;

	
	/**
	 * Via in-memory channel
	 */
	@Test
	public void testSendAndReceive() {
		input.send(new GenericMessage<String>("Hans Dampf"));
		Message<?> reply = output.receive();
		System.out.println("received: " + reply);
		assertEquals("Hello, Hans Dampf!", reply.getPayload());
	}

	/**
	 * Via JMS-backed channel
	 * @throws InterruptedException 
	 */
	@Test
	@Ignore
	public void testSendAndReceiveViaGateway() throws InterruptedException {
		Timestamp id = new Timestamp(System.currentTimeMillis());
		gateway.send("Rainer Zufall " +id);

		jmsChannel.subscribe(new MessageHandler(){

			@Override
			public void handleMessage(Message<?> message)
					throws MessagingException {
		System.out.println("received: " + message);
				
			}});
		Thread.sleep(2000);
		//String reply = gateway.receive();
//		assertEquals("Hello, Rainer Zufall!", reply);
	}
}
