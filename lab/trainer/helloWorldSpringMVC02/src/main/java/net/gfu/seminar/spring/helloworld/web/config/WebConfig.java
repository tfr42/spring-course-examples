package net.gfu.seminar.spring.helloworld.web.config;

import net.gfu.seminar.spring.helloworld.GreetingMessageService;
import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.GuestDao;
import net.gfu.seminar.spring.helloworld.config.PersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class WebConfig {

    @Bean
    public GreetingService greetingService(GuestDao guestDao) {
        return new GreetingMessageService(guestDao);
    }
}
