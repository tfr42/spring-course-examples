package net.gfu.seminar.spring.helloworld;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection and XML configuration metadata.
 *
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class Application {

    public static void main(String[] args) {
        // set the system property which is evaluated by Spring EL
        System.setProperty("isVip", "true");

        // this creates a new instance of the Spring Container aka BeanFactory
        ConfigurableApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");

        // here we retrieve an instance of Greeting from the Spring BeanFactory
        Greeting reception = (Greeting) beanFactory.getBean("greeting");

        System.out.println(reception.welcome());

        beanFactory.close();
    }

}
