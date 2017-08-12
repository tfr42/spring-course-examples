package net.gfu.seminar.spring.helloworld;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to access {@link Guest}s implementation based on plain JPA 2.1 API.
 * 
 * @author tf
 *
 */
@Repository
@Transactional
public class GuestJpaDao implements GuestDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long create(final Guest guest) {
		this.em.persist(guest);
		return guest.getId();
	}

	@Override
	@Transactional(readOnly=true)
	public Guest findById(final Long id) {
		return this.em.find(Guest.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Guest> findByName(final String name) {
		 TypedQuery<Guest> namedQuery = this.em.createNamedQuery("forName", Guest.class);
		 namedQuery.setParameter(1, name);
		 return namedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Guest> findAll() {
		final String jpquery = "Select g from Guest as g";
		final TypedQuery<Guest> query = em.createQuery(jpquery,Guest.class);
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Guest update(final Guest guest) {
		if (guest.getId() == null)
			create(guest);
		return this.em.merge(guest);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final Guest guest) {
		this.em.remove(this.update(guest));
	}
}
