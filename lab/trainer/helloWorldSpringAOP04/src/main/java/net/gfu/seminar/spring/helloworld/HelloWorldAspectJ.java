package net.gfu.seminar.spring.helloworld;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Runs Spring with native AspectJ aspect using compile-time weaving.
 */
public class HelloWorldAspectJ {
    public static void main(String[] args){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Greeting greeting = context.getBean("greeting", Greeting.class);

        System.out.println(greeting.welcome());

        context.close();
    }
}
