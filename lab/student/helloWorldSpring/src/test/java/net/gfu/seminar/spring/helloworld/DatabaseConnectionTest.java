package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles(profiles={"dev"})
public class DatabaseConnectionTest {
	
	@Inject
	private DataSource datasource;

	@Test @Repeat(5)
	public void verifyThatDatabaseConnectionIsValid() throws SQLException {
		assertTrue(datasource.getConnection().isValid(100));
	}

}
