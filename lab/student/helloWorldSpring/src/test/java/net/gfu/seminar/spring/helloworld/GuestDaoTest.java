package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import net.gfu.seminar.spring.helloworld.dao.GuestRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles("dev")
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class GuestDaoTest {

	@Autowired
	private GuestRepository dao;

	@Autowired
	@Qualifier("guest")
	private Guest testDataGuest;

	@Autowired
	private PlatformTransactionManager tx;

	@Test
	public void testCreate() {
		Guest guest = dao.save(testDataGuest);
		assertTrue(guest.getId() > 0);
	}

	@Test
	public void testFindById() {
		Guest guest = dao.save(testDataGuest);
		Long id = guest.getId();
		assertNotNull(dao.findById(id));
	}

	@Test
	public void testFindByName() {
		Guest guest = dao.save(testDataGuest);
		String name = "Dampf";
		List<Guest> list = dao.findByName(name);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testFindAll() {
		Iterable<Guest> all = dao.findAll();
		int counter = 0;
		for (Guest guest : all) {
			assertNotNull(guest);
			counter++;
		}
		assertEquals(dao.count(), counter);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testUpdate() {
		dao.save(testDataGuest);
	}

	@Test
	public void testRemove() {
		dao.delete(testDataGuest);
	}

}