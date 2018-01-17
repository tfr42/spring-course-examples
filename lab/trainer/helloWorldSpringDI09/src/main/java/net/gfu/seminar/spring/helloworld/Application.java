package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Starts the HelloWorld application using Spring Framework Dependency Injection and Annotations as configuration metadata.
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java">Java-based container configuration</a>
 */
public class Application {

	public static void main(String[] args) {
		// Creates new ApplicationContext
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		Greeting greeting = context.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());

		context.registerShutdownHook();
		context.close();
	}
}
