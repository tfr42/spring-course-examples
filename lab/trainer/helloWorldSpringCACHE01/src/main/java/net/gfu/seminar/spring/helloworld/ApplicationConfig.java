package net.gfu.seminar.spring.helloworld;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
// @PropertySource(value={"classpath:guest.properties",
// "classpath:jdbc.properties"})
public class ApplicationConfig {
	@Value("${lastName}")
	private String lastName;
	@Value("${firstName}")
	private String firstName;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public Greeting greeting() {
		return new Greeting(guest());
	}

	@Bean
	public Guest guest() {
		// return new GuestImpl("Rainer", "Unsinn");
		return new GuestImpl(firstName, lastName);
	}

	/**
	 * Requires @EnableAspectJAutoProxy at type level.
	 */
	@Bean
	public MethodCallCounterAdvice advice() {
		return new MethodCallCounterAdvice();
	}

	@Bean // destroyMethod is not required since the container automatically register the method "close()"
	public DataSource datasource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		return bds;
	}

	@Bean
	public GuestDao jdbcDao() {
		return new GuestJdbcDao();
	}
	
	@Bean
	public JdbcTemplate jt() {
		return new JdbcTemplate(datasource());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}

	/**
	 * Resource for PSPC can be defined with @PropertySource at type level.
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("guest.properties");
		locations[1] = new ClassPathResource("jdbc.properties");
		propertySourcesPlaceholderConfigurer.setLocations(locations);
		return propertySourcesPlaceholderConfigurer;
	}
}