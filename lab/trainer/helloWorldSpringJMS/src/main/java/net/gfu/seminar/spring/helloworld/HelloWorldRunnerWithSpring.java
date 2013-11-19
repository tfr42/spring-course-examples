package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection.
 * 
 * @author tf
 * @see <a href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath. 
		// TODO Open the Eclipse Project properties and check the Java Build Path
//		Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/test/resources
		
		// this creates a new instance of the Spring Container aka BeanFactory
	//	BeanFactory beanFactory = new XmlBeanFactory(resource);
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml","scheduling.xml");

		DoItExecutor doItExecutor = beanFactory.getBean(DoItExecutor.class);
		doItExecutor.doIt();
		
		// here an instance of Greeting is retrieved from the Spring BeanFactory
		GreetingService reception = (GreetingService) beanFactory.getBean("welcome");
		
		System.out.println(reception.welcome());
		beanFactory.close();
	}

}
