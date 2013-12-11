package net.gfu.seminar.spring.helloworld.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestDao;
import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import net.gfu.seminar.spring.helloworld.config.PersistenceConfig;
import net.gfu.seminar.spring.helloworld.jdbc.TestConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class,
		ApplicationConfig.class, TestConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class JpaGuestDaoTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private GuestDao dao;

	@Before
	public void setUp() throws Exception {
		System.out.println("Start test");
	}

	@BeforeTransaction
	public void beforeTransaction() {
		System.out.println("Start Tx");
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("End Tx");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("End test");
	}

	@Test
	public void test() {
		assertNotNull(em);
	}

	@Test
	@Transactional
	public void testCreate() {
		Guest guest = new Guest("Rainer", "Fall");
		assertEquals(Long.valueOf(4l), dao.create(guest));
	}

	@Test
	public void testFindById() {
		Long id = dao.findByName("Dampf").get(0).getId();
		assertNotNull(dao.findById(id));
	}

	@Test
	public void testFindByName() {
		String name = "Gramm";
		List<Guest> list = dao.findByName(name);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testFindAll() {
		List<Guest> all = dao.findAll();
		assertNotNull(all);
		assertTrue(all.size() > 0);
		assertEquals(3, all.size());
	}

	@Test
	public void testUpdate() {
		Guest guest = dao.findAll().get(0);
		guest.setName("Peter Lustig");
		dao.update(guest);
		assertEquals("Peter", guest.getFirstName());
		assertEquals("Lustig", guest.getLastName());
	}

	@Test
	@Transactional
	public void testDelete() {
		Guest guest = dao.findAll().get(0);
		dao.remove(guest);
		assertNull(dao.findById(guest.getId()));
		
	}

}
