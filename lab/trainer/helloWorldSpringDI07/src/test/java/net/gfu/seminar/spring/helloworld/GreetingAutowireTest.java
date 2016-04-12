package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class GreetingAutowireTest {

	@Test
	public void testAutowiringByConstructor() {
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("autowireConstructor.xml");
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");
		assertThat(greeting.welcome(), allOf(containsString("Anna"), containsString("Gramm")));
		beanFactory.close();
	}

	@Test
	public void testAutowiringOnBeanLevel() {
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("autowireConstructorPerBean.xml");
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");
		assertThat(greeting.welcome(), allOf(containsString("Rainer"), containsString("Fall")));
		beanFactory.close();
	}
}
