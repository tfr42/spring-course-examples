package net.gfu.seminar.spring.lombok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"net.gfu.seminar.spring.lombok"})
public class ApplicationConfig {

    @Bean(initMethod = "init")
    public Guest guest() {
        return new Guest("Hans", "Dampf");
    }

    @Bean
    public Guest specialGuest() {
        return new Guest("Anna", "Gramm");
    }

}
