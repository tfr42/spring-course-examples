package net.gfu.seminar.spring.helloworld;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DateTest {
	
	@Test
	public void testDateFactoryMethod() {
		ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("guest-properties-config.xml");
		Date now = beanFactory.getBean("now", Date.class);
		System.out.println(now);
		beanFactory.close();
	}
	
	@Test
	public void testCalendar() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		System.out.println(time);
	}
	

}
