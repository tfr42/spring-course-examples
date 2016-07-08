package net.gfu.seminar.spring.helloworld.config;

import net.gfu.seminar.spring.helloworld.Greeting;
import net.gfu.seminar.spring.helloworld.Guest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Greeting greeting() {
        return new Greeting(guest());
    }

    @Bean
    public Guest guest() {
        return new Guest("Rainer", "Fall");
    }

}