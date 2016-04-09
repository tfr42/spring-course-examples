package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection and XML configuration metadata.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath. 
		// TODO Open the Eclipse Project properties and check the Java Build Path
		//Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources
		
		// this creates a new instance of the Spring Container aka BeanFactory
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		GreetingService reception = (GreetingService) beanFactory.getBean("greeting");
		
		System.out.println(reception.welcome());
		
		beanFactory.close();
	}

}
