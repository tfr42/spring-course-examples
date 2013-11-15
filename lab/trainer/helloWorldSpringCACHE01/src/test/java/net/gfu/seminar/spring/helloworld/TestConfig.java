package net.gfu.seminar.spring.helloworld;

import javax.management.MBeanServerFactory;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@ImportResource(value={"classpath:tracingContext.xml","classpath:ehcacheContext.xml"})
@EnableMBeanExport(defaultDomain="net.gfu.seminar.spring.helloworld", registration=RegistrationPolicy.REPLACE_EXISTING)
public class TestConfig {
	
	@Bean(autowire=Autowire.BY_TYPE)
	public DataSourceInitializer dsi() {
		DataSourceInitializer dsi = new DataSourceInitializer();
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("drop_hsql_schema.sql"));
		databasePopulator.addScript(new ClassPathResource("create_hsql_schema.sql"));
		databasePopulator.addScript(new ClassPathResource("insert_testdata_hsql.sql"));
		dsi.setDatabasePopulator(databasePopulator);
		return dsi;
	}
	
	/*
	 @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:/create_hsql_schema.sql")
            .addScript("classpath:/insert_testdata_hsql.sql")
            .build();
    }
	 */
	
	//@Bean server="mbeanServer",
	public MBeanServerFactoryBean mbeanServer(){
		System.out.println(MBeanServerFactory.findMBeanServer(null));
		MBeanServerFactoryBean mBeanServerFactoryBean = new MBeanServerFactoryBean();
		mBeanServerFactoryBean.setLocateExistingServerIfPossible(true);
		return mBeanServerFactoryBean;
	}
}
