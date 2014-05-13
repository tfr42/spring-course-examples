package net.gfu.seminar.spring.helloworld;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.allOf;
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
		int id = dao.create(testDataGuest);
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
		List<Guest> actual = dao.findByName(name);
		assertFalse(actual.isEmpty());
		assertThat(actual.toString(), containsString("Dampf"));
	}

	@Test
	public void testFindAll() {
		List<Guest> actual = dao.findAll();
		assertThat(actual.toString(), allOf(containsString("Hans"), containsString("Dampf")));
	}

	@Test
	public void testUpdate() {
		dao.update(testDataGuest);
	}

	@Test
	public void testRemove() {
		dao.remove(testDataGuest);
	}
	
}
