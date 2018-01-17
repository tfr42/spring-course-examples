package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starts the HelloWorld application using Spring Framework BeanFactory.
 * 
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class Application {

	public static void main(String[] args) {
		// Loads the Spring XML configuration files and initializing the ApplicationContext
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		
		// Retrieves an instance of Greeting from the Spring Container
		Greeting greeting = beanFactory.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());
		
		System.out.println("All Spring Beans retrieved from the Spring Container:");
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			System.out.println(beanDefinitionNames[i] + " = " + beanFactory.getBean(beanDefinitionNames[i]));
		}

		beanFactory.registerShutdownHook();
		beanFactory.close();
	}
}
