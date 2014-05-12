package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * HelloWorld using Spring Framework BeanFactory.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		
		// This statement loads the Spring XML configuration file 
		// The file is located in the classpath. 
		// TODO Open the Eclipse Project properties and check the Java Build Path
		Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources

		// The Bean Factory
		// This statement creates a new instance of the Spring Container aka BeanFactory
		BeanFactory beanFactory = new XmlBeanFactory(resource); 
		
		// Here we retrieve an instance of Greeting from the Spring BeanFactory
		Greeting greeting = (Greeting) beanFactory.getBean("greeting", Greeting.class);
		
		System.out.println(greeting.welcome());
	}
}
