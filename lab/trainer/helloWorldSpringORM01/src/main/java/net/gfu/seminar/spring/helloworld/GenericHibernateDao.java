package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * GenericDao implementation based on Hibernate.
 * @param <T>
 * @param <ID>
 */
public abstract class GenericHibernateDao <T, ID extends Serializable> extends HibernateDaoSupport implements GenericDao<T, ID> {
	
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public final Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@Transactional(readOnly = true)
	@Override
	public T findById(ID id) {
		return this.findById(id, false);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	protected T findById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	protected T getById(ID id, boolean lock) {
		T entity;
		if (lock)
			entity = (T) getSession().get(getPersistentClass(), id, LockOptions.UPGRADE);
		else
			entity = (T) getSession().get(getPersistentClass(), id);

		return entity;
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T exampleEntity) {
		List<T> list = this.getHibernateTemplate().findByExample(
				exampleEntity);
		return list;
	}


	@Transactional
	public T update(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public ID create(T entity) {
		Serializable generatedId = this.getHibernateTemplate().save(entity);
		return (ID) generatedId;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ID> create(List<T> entityList) {
		List<ID>  idList= new ArrayList<ID>();
		for (T entity : entityList) {
			ID id = (ID) this.getHibernateTemplate().save(entity);
			idList.add(id);
		}
		return idList;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<T> clazz, ID id) {
		T entity = this.getHibernateTemplate().load(clazz, id);
		this.getHibernateTemplate().delete(entity);

	}

	@Override
	public void delete(Collection<T> entities) {
		for (T entity : entities) {
			this.getHibernateTemplate().delete(entity);
		}
	}

}
