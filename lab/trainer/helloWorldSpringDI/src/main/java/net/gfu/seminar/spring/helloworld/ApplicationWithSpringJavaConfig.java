package net.gfu.seminar.spring.helloworld;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 *  Starts the HelloWorld application using Spring Framework BeanFactory and JavaConfig as configuration metadata.
 *
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java">Java-based container configuration</a>
 */
public class ApplicationWithSpringJavaConfig {
	public static void main(String[] args) {
		// creates new Application Context
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ctx.registerShutdownHook(); // gracefully shutdown Spring ApplicationContext when main() ends
		System.out.println(((GreetingService) ctx.getBean("greeting")).welcome());
		ctx.close();
	}
}