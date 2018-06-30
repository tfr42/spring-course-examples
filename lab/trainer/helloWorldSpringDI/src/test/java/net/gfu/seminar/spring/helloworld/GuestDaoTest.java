package net.gfu.seminar.spring.helloworld;

import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import net.gfu.seminar.spring.helloworld.config.PersistenceConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, ApplicationConfig.class})
@ActiveProfiles("none")
@Transactional
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun_hsql.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun_hsql.sql") 
})
public class GuestDaoTest {

	private static final Logger LOG = Logger.getLogger(GuestDaoTest.class);

	@Autowired
	private GuestDao dao;
	
	@Autowired
	private JdbcTemplate jt;
	
	@Autowired
	private Guest testDataGuest;
	
	@BeforeTransaction public void beforeTx() {
		LOG.debug("Unit of work - begin");
	}
	
	@AfterTransaction public void afterTx() {
		LOG.debug("Unit of work - end");
	}
	
	@Test
	public void testCreate() {
		assertNotNull(dao);
		assertTrue(dao.create(testDataGuest) != 0);
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
	public void testDelete() {
		Guest guest = dao.findAll().get(0);
		dao.delete(guest);
		assertEquals(0, (int) jt.queryForObject("SELECT count(id) from guests where id="+guest.getId(), Integer.class));
	}

}
