package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import net.sf.ehcache.Ehcache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, TestConfig.class })
@Transactional
@Rollback
public class GuestJdbcDaoTest {

	private static final Logger LOG = Logger.getLogger(GuestJdbcDaoTest.class);

	@Autowired
	private GuestDao dao;

	@Autowired
	private Guest guest;

	@Autowired
	private JdbcTemplate jt;

	@Autowired 
	private CacheManager cache;
	
	@Test
	public void testCreateNewGuest() {
		int i = dao.create(guest);
		assertEquals(1, i);
		Integer count = jt.queryForObject(
				"Select count(*) from guests where guests.lastname=?",
				new Object[] { guest.getLastName() }, java.lang.Integer.class);
		assertEquals(1, count.intValue());
	}

	@Test
	@Repeat(3)
	public void testQueryForGuestWithId() {
		Ehcache ehcache = ((EhCacheCache) this.cache.getCache("guest")).getNativeCache();
		LOG.info("Number of instances in cache: " + ehcache.getSize());
		Long id = 1l;
		Guest guest2 = dao.findById(id);
		assertNotNull(guest2);
		LOG.info("Instances in cache: " + ehcache.getAll(ehcache.getKeys()));
	}

	@BeforeTransaction
	public void beforeTransaction() {
		LOG.info("Before Transaction");
	}

	@AfterTransaction
	public void afterTransaction() {
		LOG.info("After Transaction");
	}
}
