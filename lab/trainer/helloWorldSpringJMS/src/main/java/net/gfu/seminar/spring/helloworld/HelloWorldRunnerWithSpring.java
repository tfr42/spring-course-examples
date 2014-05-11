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
		// this creates a new instance of the Spring Container aka BeanFactory
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml","scheduling.xml");

		// here an instance of DoItExecutor is retrieved from the Spring BeanFactory and the scheduled task is started	
		DoItExecutor doItExecutor = beanFactory.getBean(DoItExecutor.class);
		doItExecutor.doIt();
		
		// here an instance of Greeting is retrieved from the Spring BeanFactory
		GreetingService reception = (GreetingService) beanFactory.getBean("welcome");
		
		System.out.println(reception.welcome());
		beanFactory.close();
	}

}
