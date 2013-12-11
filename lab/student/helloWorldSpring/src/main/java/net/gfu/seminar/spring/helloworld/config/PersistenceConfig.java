package net.gfu.seminar.spring.helloworld.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import net.gfu.seminar.spring.helloworld.GuestDao;
import net.gfu.seminar.spring.helloworld.jpa.JpaGuestDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:/jdbc.properties")
public class PersistenceConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClass;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Value("${jpa.databasePlatform}")
	private String databasePlatform;

	@Bean
	public DataSource datasource() throws PropertyVetoException {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(driverClass);
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(user);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		return p;
	}

	@Bean
	public GuestDao dao() {
		return new JpaGuestDao();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("net.gfu.seminar.spring.helloworld");
		return emf;
	}

	@Bean
	public JpaVendorAdapter hjva() {
		HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
		hjva.setDatabasePlatform(databasePlatform);
		hjva.setShowSql(true);
		hjva.setGenerateDdl(true);
		return hjva;
	}
	
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
}
