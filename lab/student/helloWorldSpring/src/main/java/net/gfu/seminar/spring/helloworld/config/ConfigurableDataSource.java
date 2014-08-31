package net.gfu.seminar.spring.helloworld.config;

import javax.sql.DataSource;

public interface ConfigurableDataSource {
	
	DataSource dataSource();

}
