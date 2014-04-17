package net.gfu.seminar.spring.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository implementation based on Spring's HibernateDaoSupport and
 * HibernateTemplate. This requires Hibernate 3.x on the classpath.
 * 
 * @author tf
 * 
 */
@Repository
@Transactional
public class GuestGenericHibernateDao extends GenericHibernateDao<Guest, Long>
		implements GuestGenericDao, GuestDao {

	@Override
	public List<Guest> findByName(String name) {
		Criterion criterion = Restrictions.eq("lastName", name);
		List<Guest> list = this.findByCriteria(criterion);
		return new ArrayList<Guest>(list);
	}

}
