package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/persistenceLayer.xml"})

public class DatabaseConnectionTest {
	
	@Autowired
	private DataSource dataSource;

	@Test @Timed(millis=1000) 
	public void testConnectionWithTimeout() throws SQLException {
		assertNotNull(dataSource);
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
		assertTrue(connection.isValid(1));
		assertEquals("HSQL Database Engine", connection.getMetaData().getDatabaseProductName());
	}
	
	@Test @Repeat(3)  @DirtiesContext
	public void testConnectionWithRepeat() throws SQLException {
		assertNotNull(dataSource);
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
		assertTrue(connection.isValid(1));
		assertEquals("HSQL Database Engine", connection.getMetaData().getDatabaseProductName());
	}

}
