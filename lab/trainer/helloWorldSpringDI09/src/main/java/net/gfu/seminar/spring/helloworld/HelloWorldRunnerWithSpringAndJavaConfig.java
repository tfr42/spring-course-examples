package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection and Annotations as configuration metadata.
 * @author tf
 *@see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java">Java-based container configuration</a>
 */
public class HelloWorldRunnerWithSpringAndJavaConfig {
	public static void main(String[] args) {
		// activates the profile special
		System.setProperty("spring.profiles.active", "special");
		// creates new Application Context
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		System.out.println((ctx.getBean("greeting", GreetingService.class)).welcome());

		ctx.close();
	}
}
