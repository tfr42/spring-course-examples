package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * HelloWorld using Dependency Injection based on Spring Framework.
 * 
 * @author tf
 * @see <a href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 * @see  org.springframework.beans.factory.BeanFactory
 */
public class HelloWorldRunnerWithSpringJavaConfig {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new
				AnnotationConfigApplicationContext(ApplicationConfig.class);
		GreetingService greeting = context.getBean(GreetingService.class);
		
		System.out.println(greeting.welcome());

	}

}
