package net.gfu.seminar.spring.helloworld.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"net.gfu.seminar.spring.helloworld.web"})
public class DispatcherConfig {

	
}
