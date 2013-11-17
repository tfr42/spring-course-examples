package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GuestHibernateDao extends HibernateDaoSupport implements GuestDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int create(Guest guest) {
		return ((Long)this.getHibernateTemplate().save(guest)).intValue();
	}

	@Override
	public Guest findById(Long id) {
		return this.getHibernateTemplate().get(GuestImpl.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findByName(String name) {
		return this.getHibernateTemplate().find("from GuestImpl as g where g.lastName = ?",name );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findAll() {
		return this.getHibernateTemplate().find("from GuestImpl");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Guest update(Guest guest) {
		this.getHibernateTemplate().saveOrUpdate(guest);
		return guest;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Guest guest) {
		this.getHibernateTemplate().delete(guest);
	}

}
