package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml","classpath:/persistenceLayer.xml", "classpath:/testData.xml"})
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
		assertEquals(1, all.size());
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
