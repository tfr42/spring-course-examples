package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * HelloWorld using Spring Framework Dependency Injection and XML configuration metadata.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath. 
		Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources
		
		// this creates a new instance of the Spring Container aka BeanFactory
		BeanFactory beanFactory = new XmlBeanFactory(resource); 
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		Greeting greeting = beanFactory.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());
	}
}
