package net.gfu.seminar.spring.helloworld;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInitializer {
	
	private static final Logger LOG = Logger.getLogger(DatabaseInitializer.class);
	private static final String DELIMITER = ";";

	private DataSource dataSource;
	private List<ClassPathResource> resources;

	public void init() {
		for (ClassPathResource scriptResource : resources) {
			try {
				execute(scriptResource);
			} catch (IOException e) {
				LOG.error(e.getLocalizedMessage(), e);
			}
		}
	}

	private void execute(ClassPathResource scriptResource) throws IOException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		if (scriptResource.exists() && scriptResource.isReadable() ) {
			LOG.info("Reading from [" + scriptResource.getDescription()+"]");
			Scanner scanner = new Scanner(scriptResource.getFile());
			scanner.useDelimiter(DELIMITER);
			try {
				while (scanner.hasNext()) {
					String statement = (String) scanner.next();
					jdbcTemplate.execute(statement+DELIMITER);
				}
			} catch (Exception e) {
				LOG.error(e.getLocalizedMessage(), e);
			} finally {
				scanner.close();
			}
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setResources(List<ClassPathResource> resources) {
		this.resources = resources;
	}

}