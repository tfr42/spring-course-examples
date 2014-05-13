package net.gfu.seminar.spring.helloworld;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInitializer {

	private DataSource dataSource;
	private List<ClassPathResource> resources = new ArrayList<ClassPathResource>();

	public void init() {
		for (ClassPathResource scriptResource : getResources()) {
			try {
				execute(scriptResource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void execute(ClassPathResource scriptResource) throws IOException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		if (scriptResource.exists() && scriptResource.isReadable() ) {
			String url = scriptResource.getURL().toString();
			System.out.println("Executing " + url);
			BufferedReader br = new BufferedReader(new FileReader(scriptResource.getFile()));
			String line;
		    while ((line = br.readLine()) != null) {
		    	System.out.println("executing SQL :" + line);
		      jdbcTemplate.execute(line);
		    }
		    br.close();
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<ClassPathResource> getResources() {
		return resources;
	}

	public void setResources(List<ClassPathResource> resources) {
		this.resources = resources;
	}

}
