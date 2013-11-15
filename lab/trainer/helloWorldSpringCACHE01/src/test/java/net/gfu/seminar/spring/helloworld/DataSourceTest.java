package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class DataSourceTest {

	@Autowired
	private DataSource datasource;

	@Test @Timed(millis=1000)
	public void testDatasource() throws SQLException {
		assertNotNull(datasource);
		Connection connection = datasource.getConnection();
		assertNotNull(connection);
		assertTrue(connection.isValid(1));
		connection.close();
	}
}
