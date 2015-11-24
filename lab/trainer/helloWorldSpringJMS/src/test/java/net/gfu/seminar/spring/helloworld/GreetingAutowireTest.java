package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingAutowireTest {

	@Test
	public void testAutowire() {
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("autowireConstructor.xml");
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");
		System.out.println(greeting.welcome());
		beanFactory.close();
	}

	@Test
	public void testAutowirePerBean() {
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("autowireConstructorPerBean.xml");
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");
		System.out.println(greeting.welcome());
		beanFactory.close();
	}
}
