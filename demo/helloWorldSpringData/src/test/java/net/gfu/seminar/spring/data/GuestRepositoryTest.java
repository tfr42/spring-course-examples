package net.gfu.seminar.spring.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
public class GuestRepositoryTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private GuestRepository guestRepository;

	@Before
	public void setUp() throws Exception {
		System.out.println("Start test");
		Session session = em.unwrap(Session.class);
		session.doWork(new Work() {
	        @Override
	        public void execute(Connection connection) throws SQLException {
	            connection.createStatement().executeUpdate("INSERT INTO GUEST (firstname,lastname) VALUES ('Hans','Dampf')");
	            connection.createStatement().executeUpdate("INSERT INTO GUEST (firstname,lastname) VALUES ('Anna','Gramm')");
	            connection.createStatement().executeUpdate("INSERT INTO GUEST (firstname,lastname) VALUES ('Rainer','Unsinn')");
	        }
	    });
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
	public void testEnityManager() {
		assertNotNull(em);
	}

	@Test
	public void testCreate() {
		Guest guest = new Guest("Rainer", "Fall");
		assertNull(guest.getId());
		assertNotNull( guestRepository.save(guest).getId());
	}

	@Test
	public void testFindById() {
		Long id = guestRepository.findByName("Dampf").get(0).getId();
		assertNotNull(guestRepository.findById(id));
	}

	@Test
	public void testFindByName() {
		String name = "Gramm";
		List<Guest> list = guestRepository.findByName(name);
		assertEquals(1, list.size());
	}

	@Test
	public void testFindAll() {
		Iterable<Guest> all = guestRepository.findAll();
		for (Guest guest : all) {
			assertNotNull(guest);
		}
	}

	@Test
	public void testFindGuestByLastNameOrFirstNameAllIgnoreCaseOrderByLastNameDesc() {
		String firstName = "RaiNer";
		String lastName = "DAMpf";
		List<Guest> list = guestRepository.findByFirstNameOrLastNameAllIgnoreCaseOrderByLastNameDesc(firstName,lastName);
		assertEquals(2, list.size());
	}

	@Test
	public void testUpdate() {
		final Guest guest = guestRepository.findAll().iterator().next();
		guest.setName("Peter Lustig");
		final Guest guest2 = guestRepository.save(guest);
		assertEquals("Peter", guest2.getFirstName());
		assertEquals("Lustig", guest2.getLastName());
	}

	@Test
	public void testDelete() {
		Guest guest = guestRepository.findAll().iterator().next();
		guestRepository.delete(guest);
		assertNull(guestRepository.findById(guest.getId()));
	}
}
