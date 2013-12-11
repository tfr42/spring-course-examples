package net.gfu.seminar.spring.helloworld.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class JpaGuestDao implements GuestDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long create(Guest guest) {
		em.persist(guest);
		return guest.getId();
	}

	@Override
	public Guest findById(Long id) {
		return em.find(Guest.class, id);
	}

	@Override
	public List<Guest> findByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Guest> query = em.createQuery(
				"from Guest as g where g.lastName = :name", Guest.class);
		query.setParameter("name", name);
		List<Guest> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Guest> findAll() {
		return em.createQuery("from Guest",Guest.class).getResultList();
	}

	@Override
	public Guest update(Guest guest) {
		return em.merge(guest);
	}

	@Override
	public void remove(Guest guest) {
		em.remove(guest);
	}

}
