package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection and Annotations as configuration metadata.
 * @author tf
 *@see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java">Java-based container configuration</a>
 */
public class HelloWorldRunnerWithSpringAndAnnotation {
	public static void main(String[] args) {
		// creates new Application Context
		GenericApplicationContext ctx = new GenericApplicationContext();
		
		// scans for Spring Beans annotated with @Component or @Configuration
		new ClassPathBeanDefinitionScanner(ctx)
				.scan("net.gfu.seminar.spring.helloworld");
		
		// creates all bean instances
		ctx.refresh(); 
		
		System.out.println((ctx.getBean("greeting",GreetingService.class)).welcome());
		ctx.close();
	}
}
