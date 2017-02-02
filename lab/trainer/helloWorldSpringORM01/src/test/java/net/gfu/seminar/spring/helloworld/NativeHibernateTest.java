package net.gfu.seminar.spring.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Testing native Hibernate API without Spring ORM support.
 */
public class NativeHibernateTest {
    @Test
    public void testSaveGuestIntoInMemoryDatabase() {
        // Arrange fixture & build
        Guest guest = new Guest();
        guest.setFirstName("Hans");
        guest.setLastName("Dampf");

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Guest.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:testdb")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        // Act & operate
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(guest);
        transaction.commit();

        // Assert & check
        assertTrue(session.contains(guest));
        // cleanup
        session.close();
    }
}
