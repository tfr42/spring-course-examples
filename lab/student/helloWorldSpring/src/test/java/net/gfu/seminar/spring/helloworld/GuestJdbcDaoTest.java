package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
@ActiveProfiles("dev")
public class GuestJdbcDaoTest {
	@Autowired
	private GuestDao dao;
	@Autowired
	private Guest guest;

	@Test
	@Transactional
	public void testCreate() {
		assertNotNull(guest);
		assertNotNull(dao);
		Long numberOfAffectedRows = dao.create(guest);
		assertEquals(1, numberOfAffectedRows.intValue());
	}

	@Test
	public void testFindByName() {
		List<Guest> guestList = dao.findByName("Dampf");
		assertFalse(guestList.isEmpty());
	}
}