package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class GreetingAutowireTest {

	@Test
	public void testAutowire() {
		Resource resource = new ClassPathResource("autowireConstructor.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");

		System.out.println(greeting.welcome());
	}

	@Test
	public void testAutowirePerBean() {
		Resource resource = new ClassPathResource(
				"autowireConstructorPerBean.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		Greeting greeting = (Greeting) beanFactory.getBean("welcome");

		System.out.println(greeting.welcome());
	}
}
