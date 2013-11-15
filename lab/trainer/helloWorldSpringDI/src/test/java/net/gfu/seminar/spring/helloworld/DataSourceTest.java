package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
public class DataSourceTest {
	private static final Logger LOG = Logger.getLogger(DataSourceTest.class);
	
	@Inject
	private DataSource ds;
	
	@Timed(millis=100)
	@Test public void testDataSourceConnection() throws SQLException{
		Connection connection = ds.getConnection();
		assertTrue(connection.isValid(100));
		connection.close();
	}
	
	@Test
	public void testPlainJdbcQuery() throws ClassNotFoundException, SQLException {
		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT firstname, lastname FROM guests");
		while (rs.next()) {
		 String vorname = rs.getString(1);
		 String nachname = rs.getString(2);
		 Guest guest = new GuestImpl(vorname, nachname);
		 assertNotNull(guest);
		}
		rs.close();
		stmt.close();
		con.close();
	}
	
	@Rule
	public TestName testName = new TestName();
	
	@Before public void setUp() {
		LOG.debug("Test " + testName.getMethodName() + " started");
	}
	@After public void tearDown() {
		LOG.debug("Test " + testName.getMethodName() + " completed");
	}

}
