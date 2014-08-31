package net.gfu.seminar.spring.helloworld.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:/jdbc-dev.properties")
@Profile("dev")
public class DevDataSourceConfig implements ConfigurableDataSource {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	@Override
	public DataSource dataSource() {
		return new DriverManagerDataSource(url, username, password);
	}

}
