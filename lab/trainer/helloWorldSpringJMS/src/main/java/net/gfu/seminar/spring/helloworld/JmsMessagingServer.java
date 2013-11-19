package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starts JMS Message Listener Container for asynchronous messaging.
 * @author tf
 *
 */
public class JmsMessagingServer {

    private ConfigurableApplicationContext classPathXmlApplicationContext;

	public static void main(String[] args) throws Exception {
        new JmsMessagingServer().start();
    }
    
    public void start() {
    	classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:/jms-listener.xml", "classpath:/jms-config.xml", "classpath:/persistenceLayer.xml"});
    	classPathXmlApplicationContext.start();
    }
    
    public void stop() {
    	classPathXmlApplicationContext.stop();
    	classPathXmlApplicationContext.close();
    }

}