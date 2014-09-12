package net.gfu.seminar.spring.helloworld;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to access {@link Guest}s using Spring {@Link JdbcTemplate} 
 * 
 * @author tf
 *
 */
@Repository
@Transactional
public class GuestJdbcDao implements GuestDao {
	private static final Logger LOG = Logger.getLogger(GuestJdbcDao.class);
	@Autowired
	private JdbcTemplate jt;

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public int create(Guest guest) {
		String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName() };
		int updatedRows = jt.update(sql, args);
		LOG.debug(updatedRows + " rows updated");
		return updatedRows;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
	@Cacheable(value = "guest", condition = "#id>0")
	public Guest findById(Long id) {
		String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE id=?";
		LOG.debug(sql);
		Object[] args = new Object[] { id };
		List<Guest> list = jt.query(sql, createRowMapper(), args);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	@Cacheable(value = "guest")
	public List<Guest> findByName(String name) {
		String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE lastname=?";
		LOG.debug(sql);
		Object[] args = new Object[] { name };
		List<Guest> list = jt.query(sql, createRowMapper(), args);
		return list;
	}

	@Override
	@Cacheable(value = "guest")
	public List<Guest> findAll() {
		String sql = "SELECT id, firstname, lastname FROM GUESTS";
		LOG.debug(sql);
		Object[] args = new Object[] {};
		List<Guest> list = jt.query(sql, createRowMapper(), args);
		return list;
	}

	@Override
	@CachePut(value="guest")
	public Guest update(Guest guest) {
		if (guest.getId() == null) {
			this.create(guest);
		}
		String sql = "UPDATE guests SET  firstname = ?, lastname=? WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName(), guest.getId() };
		int updatedRows = jt.update(sql, args);
		LOG.debug(updatedRows + " rows updated");
		return guest;
	}

	@Override
	@CacheEvict(value="guest")
	public void remove(Guest guest) {
		String sql = "DELETE FROM GUESTS WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getId() };
		int updatedRows = jt.update(sql, args);
		LOG.debug(updatedRows + " rows updated");

	}

	private RowMapper<Guest> createRowMapper() {
		return new RowMapper<Guest>() {
			@Override
			public Guest mapRow(ResultSet rs, int row) throws SQLException {
				return new Guest(rs.getLong("id"), rs.getString("firstname"),
						rs.getString("lastname"));
			}
		};
	}

}
