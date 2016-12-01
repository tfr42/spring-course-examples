package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/persistenceLayer.xml"})
public class GuestDaoTest {
	
	@Autowired
	private GuestDao dao;
	
	@Autowired
	@Qualifier("guest")
	private Guest testDataGuest;

	@Test
	public void testCreate() {
		Long id = dao.create(testDataGuest);
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
	public void testRemove() {
		dao.delete(testDataGuest);
	}

}
