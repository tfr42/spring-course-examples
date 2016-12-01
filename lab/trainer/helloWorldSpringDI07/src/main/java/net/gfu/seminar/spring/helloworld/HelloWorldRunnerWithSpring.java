package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		// loading the Spring XML configuration file and initializing the ApplicationContext
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// here we retrieve an instance of Greeting from the Spring Container
		Greeting greeting = context.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());

		context.registerShutdownHook();
		context.close();

	}

}
