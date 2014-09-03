package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@ActiveProfiles("dev")
public class DataSourceTest {

	@Autowired
	private DataSource dataSource;

	@Test
	public void test() throws SQLException {
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
		assertTrue(connection.isValid(100));
	}

}
