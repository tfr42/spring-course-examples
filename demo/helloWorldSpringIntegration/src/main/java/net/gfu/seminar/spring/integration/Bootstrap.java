package net.gfu.seminar.spring.integration;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {
 
    public static void main(String[] args) {
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/si-config.xml");
    }
 
}
