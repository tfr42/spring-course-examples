package net.gfu.seminar.spring.helloworld.groovy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * HelloWorld using Spring Framework <code>GenericGroovyApplicationContext</code> and Groovy DSL.
 * 
 * @author tf
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#groovy-bean-definition-dsl">Spring Groovy DSL</a>
 */
public class Application {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container with Groovy
		// 1. The Application Context is configured by Groovy DSL
		// this creates a new instance of the Spring Container aka ApplicationContext
		ConfigurableApplicationContext context = new GenericGroovyApplicationContext("beans.groovy");
		
		// here we retrieve an instance of Greeting from the Spring BeanFactory
		Greeting greeting = context.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());

		context.close();
	}
}