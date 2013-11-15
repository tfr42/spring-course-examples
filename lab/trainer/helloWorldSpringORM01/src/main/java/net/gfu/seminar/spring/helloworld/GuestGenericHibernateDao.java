package net.gfu.seminar.spring.helloworld;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
@Repository
public class GuestGenericHibernateDao extends GenericHibernateDao<Guest, Long>
		implements GuestGenericDao {
	
	@Override
	public List<Guest> findByName(String name) {
		Criterion criterion = Restrictions.eq("nachname", name);
		List<Guest> list = this.findByCriteria(criterion);
		return new ArrayList<Guest>(list);
	}
	
}
