package net.gfu.seminar.spring.helloworld;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value="classpath:/jdbc.properties")
@EnableAspectJAutoProxy
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
		GuestJdbcDao guestJdbcDao = new GuestJdbcDao();
		guestJdbcDao.setDataSource(dataSource());
		return guestJdbcDao;
	}
	
	@Bean
	public GreetingService greeting() {
		return new Greeting(guestDao());
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
}
