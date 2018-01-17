package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Starts the HelloWorld application using Spring Framework BeanFactory.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class Application {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// The Bean Factory
		// Loads the Spring XML configuration file form classpath resource.
		// TODO Open in your IDE the project settings and check the Java Build Path
		Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources
		
		// Creates a new instance of the Spring Container
		BeanFactory beanFactory = new XmlBeanFactory(resource); 
		
		// Retrieves an instance of Greeting from the Spring BeanFactory
		Greeting greeting = beanFactory.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());
	}
}