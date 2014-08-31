package net.gfu.seminar.spring.helloworld.cli;

import net.gfu.seminar.spring.helloworld.Greeting;
import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * HelloWorld using Spring Framework BeanFactory.
 * 
 * @author tf
 * @see <a
 *      href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring
 *      DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		// / XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the
		// BeanFactory
		// this file is located in the classpath.
		// TODO Open the Eclipse Project properties and check the Java Build
		// Path
		// Resource resource = new ClassPathResource("applicationContext.xml");
		// //located in src/main/resources

		// this creates a new instance of the Spring Container aka BeanFactory
		// BeanFactory beanFactory = new XmlBeanFactory(resource);
		// ClassPathXmlApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		// AnnotationConfigApplicationContext applicationContext = new
		// AnnotationConfigApplicationContext(ApplicationConfig.class);
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		// instead of using a environment variable
		// "-Dspring.profiles.active=dev"
		applicationContext.getEnvironment().addActiveProfile("dev");
		applicationContext.register(ApplicationConfig.class);
		applicationContext.refresh();

		// here we retrieve an instance of Greeting from the Spring BeanFactory
		Greeting greeting = applicationContext.getBean("greeting",
				Greeting.class);

		System.out.println(greeting.welcome());

		applicationContext.close();
	}
}
