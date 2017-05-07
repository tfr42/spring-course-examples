package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * HelloWorld using Spring Framework Dependency Injection and Annotations as configuration metadata.
 *
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java">Java-based container configuration</a>
 */
public class HelloWorldRunnerWithSpringAnnotation {

    public static void main(String[] args) {
        // set the system property which is evaluated by Spring EL
        System.setProperty("isVip", "true");

        // creates new Application Context
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println(((Greeting) ctx.getBean("greeting")).welcome());
        ctx.close();
    }
}