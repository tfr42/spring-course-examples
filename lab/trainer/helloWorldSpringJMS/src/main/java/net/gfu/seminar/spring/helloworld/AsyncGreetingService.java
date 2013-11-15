package net.gfu.seminar.spring.helloworld;

import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class AsyncGreetingService extends Greeting {
	
	private static final Logger LOG = Logger.getLogger(AsyncGreetingService.class);
	
	private JmsTemplate jmsTemplate;
	private Queue queue;
	private List<Guest> guests;

	public AsyncGreetingService(List<Guest> guests) {
		this.guests = guests;
	}
	
    public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }


	@Override
	public String welcome() {
		for (Guest guest : this.guests) {
			final String message = String.format("Welcome %1$s to Spring!", guest.getName());
			try {
				LOG.debug("Sending message: "+ message + " to queue: "+ queue.getQueueName());
			} catch (JMSException e) {
				e.printStackTrace();
			}
			this.jmsTemplate.send(this.queue, new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	              return session.createTextMessage(message);
	            }
	        });	
		}
		
		return "messages sent";
	}

}
