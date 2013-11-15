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
		//Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources
		
		// this creates a new instance of the Spring Container aka BeanFactory
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml"); 
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		Greeting reception = (Greeting) beanFactory.getBean("welcome");
		
		System.out.println(reception.welcome());
		
		System.out.println("Inhalt der BeanFactory:");
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			System.out.println(beanDefinitionNames[i] + " = " + beanFactory.getBean(beanDefinitionNames[i]));
		}
		beanFactory.close();
	}

}
