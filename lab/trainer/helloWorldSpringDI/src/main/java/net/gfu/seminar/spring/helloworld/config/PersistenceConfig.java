package net.gfu.seminar.spring.helloworld.config;

import javax.sql.DataSource;

import net.gfu.seminar.spring.helloworld.GuestDao;
import net.gfu.seminar.spring.helloworld.GuestJdbcDao;
import net.gfu.seminar.spring.helloworld.GuestJdbcPrepareStatementDao;
import net.gfu.seminar.spring.helloworld.GuestJdbcSimpleInsertDao;
import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/jdbc.properties")
@EnableTransactionManagement
public class PersistenceConfig {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(1);
		dataSource.setMaxTotal(1);
		dataSource.setMaxWaitMillis(1000);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public GuestDao guestDao() {
		return new GuestJdbcDao(dataSource());
	}

	@Bean(name="dao") @Profile("PSCDao")
	public GuestDao guestPscDao() {
		return new GuestJdbcPrepareStatementDao(dataSource());
	}

	@Bean(name="dao") @Profile("SIDao")
	public GuestDao guestSiDao() {
		return new GuestJdbcSimpleInsertDao(dataSource());
	}

	@Bean
	public JdbcTemplate jt() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public DataSourceInitializer dsi() {
		DataSourceInitializer dsi = new DataSourceInitializer();
		dsi.setDataSource(dataSource());
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setScripts(new Resource[] {
				new ClassPathResource("drop_hsql_schema.sql"),
				new ClassPathResource("create_hsql_schema.sql"), 
				new ClassPathResource("insert_testdata_hsql.sql")});
		dsi.setDatabasePopulator(databasePopulator);
		return dsi;
	}

	/**
	 * This static bean is only required in case the @Value annotation is used on field level.
	 *
	 * @see http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		// This would replace the @PropertySource annotation
		// Resource[] resourceLocations = new Resource[] {
		// new ClassPathResource("guest.properties"),
		// };
		// p.setLocations(resourceLocations);
		return p;
	}

}
