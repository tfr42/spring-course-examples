package net.gfu.seminar.spring.helloworld;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class DateTest {
	
	@Test
	public void testDateFactoryMethod() {
		Resource resource = new ClassPathResource("testContext.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		Date now = beanFactory.getBean("now", Date.class);
		System.out.println(now);
	}
	
	@Test
	public void testCalendar() {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		System.out.println(time);
	}
	

}
