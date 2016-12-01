package net.gfu.seminar.spring.data;

import javax.persistence.EntityManager;

import net.gfu.seminar.spring.helloworld.JPAConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
public class GuestMappingIntegrationTest {

	@Autowired
	private EntityManager manager;

	@Test
	public void verifyThatItemCustomMappingWorks() throws Exception {
		JPAAssertions.assertTableExists(manager, "GUEST");
		JPAAssertions.assertTableHasColumn(manager, "GUEST", "FIRSTNAME");
		JPAAssertions.assertTableHasColumn(manager, "GUEST", "LASTNAME");
	}

}