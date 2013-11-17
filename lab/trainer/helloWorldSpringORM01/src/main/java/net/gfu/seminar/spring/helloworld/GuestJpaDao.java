package net.gfu.seminar.spring.helloworld;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository implementation based on Spring JpaDaoSupport. Since Spring 3.1 this implementation is deprecated. 
 * TODO: needs refactoring according to Spring documentation 
 * @author tf
 *
 */
@Repository
public class GuestJpaDao extends JpaDaoSupport implements GuestDao {
	private static final Logger LOG = Logger.getLogger(GuestJdbcDao.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int create(final Guest guest) {
		this.getJpaTemplate().persist(guest);
		System.out.println(guest);
		return 1;
	}

	@Override
	public Guest findById(final Long id) {
		return this.getJpaTemplate().find(GuestImpl.class, id);
	}

	@Override
	public List<Guest> findByName(final String name) {
		return this.getJpaTemplate().findByNamedQuery("forName", name);
	}

	@Override
	public List<Guest> findAll() {
		final String jpquery = "from GuestImpl as g";
		return this.getJpaTemplate().executeFind(
				new JpaCallback<List<Guest>>() {
					@Override
					public List<Guest> doInJpa(EntityManager em)
							throws PersistenceException {
						final Query query = em.createQuery(jpquery);
						return query.getResultList();
					}
				});
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Guest update(final Guest guest) {
		if (guest.getId() == null)
			create(guest);
		return this.getJpaTemplate().merge(guest);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final Guest guest) {
		this.getJpaTemplate().remove(this.update(guest));
	}
}
