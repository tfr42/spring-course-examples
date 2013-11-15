package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld using Dependency Injection based on Spring Framework.
 * 
 * @author tf
 * @see <a href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 * @see  org.springframework.beans.factory.BeanFactory
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath. 
		// TODO Open the Eclipse Project properties and check the Java Build Path
		// this creates a new instance of the Spring Container aka BeanFactory
		// the XML configuration metadata is located in src/main/resources
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		GreetingService reception = (GreetingService) beanFactory.getBean("welcome");
		
		System.out.println(reception.welcome());

	}

}
