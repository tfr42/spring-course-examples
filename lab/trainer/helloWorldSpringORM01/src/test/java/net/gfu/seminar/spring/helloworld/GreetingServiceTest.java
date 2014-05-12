package net.gfu.seminar.spring.helloworld;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath:/applicationContext.xml" })
@Transactional
public class GreetingServiceTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private GreetingService service;
	
	@Before
	public void setUp() throws Exception {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Rainer','Fall')");
	}

	@Test
	public void testWelcome() {
		String message = service.welcome();
		System.out.println(message);
		assertThat(message, containsString("Fall"));
	}

}
