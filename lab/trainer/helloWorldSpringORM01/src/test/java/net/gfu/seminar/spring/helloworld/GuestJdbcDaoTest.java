package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
// TODO: choose profiles: jdbc, hibernate
@ActiveProfiles(profiles = { "jdbc" }) 
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class GuestJdbcDaoTest {

	@Autowired
	private GuestDao dao;
	
	@Autowired
	private JdbcTemplate jt;

	@Before
	public void setUp() throws Exception {
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Rainer','Unsinn')");
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Anna','Gramm')");
		jt.execute("INSERT INTO guests (firstname,lastname) VALUES ('Hans','Dampf')");
	}

	@BeforeTransaction
	public void beforeTransaction() {
		System.out.println("Before Tx");
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("After Tx");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void testCreate() {
		Guest guest = new Guest("Rainer", "Fall");
		assertNotNull(dao.create(guest));
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
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
	@Rollback(true)
	public void testFindAll() {
		List<Guest> all = dao.findAll();
		System.out.println(all);
		assertNotNull(all);
		assertTrue(all.size() > 0);
		assertEquals(3, all.size());
	}

	@Test
	public void testUpdate() {
		Guest guest = dao.findAll().get(0);
		guest.setName("Peter Lustig");
		dao.update(guest);
		assertEquals(
				"Peter",
				jt.queryForObject("SELECT firstname from guests where id="
						+ guest.getId(), String.class));
		assertEquals(
				"Lustig",
				jt.queryForObject("SELECT lastname from guests where id="
						+ guest.getId(), String.class));
	}

	@Test
	@Transactional
	public void testDelete() {
		Guest guest = dao.findAll().get(0);
		dao.delete(guest);
		assertEquals(
				0,
				(int) jt.queryForObject("SELECT count(id) from guests where id="
						+ guest.getId(), Integer.class));
	}
	
	@Test @Ignore
	public void testNativeHibernate() {
		Guest guest = new Guest();
		guest.setFirstName("Hans");
		guest.setLastName("Dampf");
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(guest);
		transaction.commit();
		session.close();
	}

}
