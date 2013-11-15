package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
@Transactional
public class GuestDaoTest {

	private static final Logger LOG = Logger.getLogger(GuestDaoTest.class);
	@Autowired
	private GuestDao dao;
	
	@Autowired
	private JdbcTemplate jt;
	
	@Autowired
	private Guest testDataGuest;
	
	@Before public void setup() {
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Rainer','Unsinn')");
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Anna','Gramm')");
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Hans','Dampf')");
	}
	
	@BeforeTransaction public void beforeTx() {
		LOG.debug("Tx begin");
	}
	
	@AfterTransaction public void afterTx() {
		LOG.debug("Tx end");
	}
	
	@Test
	public void testCreate() {
		assertNotNull(dao);
		assertEquals(1, dao.create( testDataGuest));
		assertNotNull(testDataGuest.getId());
	}

	@Test
	public void testFindById() {
		Long id = Long.valueOf(1);
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
		assertTrue( dao.findAll().size()>0);
	}

	@Test
	public void testUpdate() {
		Guest guest = dao.findAll().get(0);
		guest.setName("Peter Lustig");
		dao.update(guest);
		assertEquals("Peter", jt.queryForObject("SELECT firstname from guests where id="+guest.getId(), String.class));
		assertEquals("Lustig", jt.queryForObject("SELECT lastname from guests where id="+guest.getId(), String.class));
	}

	@Test
	public void testRemove() {
		Guest guest = dao.findAll().get(0);
		dao.remove(guest);
		assertEquals(0, jt.queryForInt("SELECT count(id) from guests where id="+guest.getId()));
	}

}
