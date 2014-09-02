package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class GuestMyBatisRepository extends SqlSessionDaoSupport implements
		GuestDao {

	@Override
	public Long create(Guest guest) {
		int i = getSqlSession().insert(
				"net.gfu.seminar.spring.helloworld.GuestMapper.create", guest);
		return Long.valueOf(i);
	}

	@Override
	public Guest findById(Long id) {
		return getSqlSession().selectOne(
				"net.gfu.seminar.spring.helloworld.GuestMapper.findById", id);
	}

	@Override
	public List<Guest> findByName(String name) {
		return getSqlSession().selectList(
				"net.gfu.seminar.spring.helloworld.GuestMapper.findByName", name);
	}

	@Override
	public List<Guest> findAll() {
		return getSqlSession().selectList(
				"net.gfu.seminar.spring.helloworld.GuestMapper.findAll");
	}

	@Override
	public Guest update(Guest guest) {
		int i = getSqlSession().update(
				"net.gfu.seminar.spring.helloworld.GuestMapper.update", guest);
		return guest;
	}

	@Override
	public void delete(Guest guest) {
		int i = getSqlSession().delete(
				"net.gfu.seminar.spring.helloworld.GuestMapper.delete", guest.getId());

	}

}
