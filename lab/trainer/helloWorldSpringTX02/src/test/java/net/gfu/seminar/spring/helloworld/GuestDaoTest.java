package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =  { ApplicationConfig.class })
@Transactional
@Rollback
public class GuestDaoTest {
	
	@Autowired
	private GuestDao dao;
	
	@Autowired
	@Qualifier("guest")
	private Guest testDataGuest;
	
	@Autowired
	private PlatformTransactionManager tx;
	
	@BeforeTransaction
	public void beforeTx() {
		System.out.println("Start TX");
		System.out.println(tx);
	}
	
	@AfterTransaction
	public void afterTx() {
		System.out.println("End TX");
	}
	
	@Test
	public void testCreate() {
		Long id = dao.create(testDataGuest);
		assertTrue(id>0);
	}

	@Test
	public void testFindById() {
		Long id = Long.valueOf(1);
		assertNotNull(dao.findById(id));
	}

	@Test
	public void testFindByName() {
		String name = "Dampf";
		List<Guest> list = dao.findByName(name);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testFindAll() {
		List<Guest> all = dao.findAll();
		assertTrue( all.size() > 0 );
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testUpdate() {
		dao.update(testDataGuest);
	}

	@Test
	public void testRemove() {
		dao.delete(testDataGuest);
	}
	
}
