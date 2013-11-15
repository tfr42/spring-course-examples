package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldRunnerWithAnnotationConfig {

	public static void main(String[] args) {
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("annotationContext.xml");
		GreetingService greeting = beanFactory.getBean(Greeting.class);
		System.out.println(greeting.welcome());
	}
}
