package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/persistenceLayer.xml", "classpath:/testData.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class GuestDaoTest {

	private static final Logger LOG = Logger.getLogger(GuestDaoTest.class);

	@Autowired
	private GuestDao dao;
	
	@Autowired
	@Qualifier("guest")
	private Guest testDataGuest;

	@Test
	public void testCreate() {
		int id = dao.create(testDataGuest);
		assertTrue(id>0);
	}

	@Test
	public void testFindById() {
		Long id = null;
		dao.findById(id);
	}

	@Test
	public void testFindByName() {
		String name = null;
		dao.findByName(name);
	}

	@Test
	public void testFindAll() {
		dao.findAll();
	}

	@Test
	public void testUpdate() {
		dao.update(testDataGuest);
	}

	@Test
	@Transactional(isolation=Isolation.SERIALIZABLE, propagation=Propagation.REQUIRED)
	@Rollback(true)
	public void testRemove() {
		dao.remove(testDataGuest);
	}

	@BeforeTransaction public void beforeTransaction() {
		LOG.info("Transaction begins ...");
	}
	
	@AfterTransaction public void afterTransaction() {
		LOG.info("Transaction ends ...");
	}
}
