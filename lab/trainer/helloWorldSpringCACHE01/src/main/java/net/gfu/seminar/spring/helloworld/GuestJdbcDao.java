package net.gfu.seminar.spring.helloworld;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class GuestJdbcDao implements GuestDao {

	@Autowired
	private JdbcTemplate jt;

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	public int create(Guest guest) {
		String sql = "INSERT INTO guests (firstname,lastname) VALUES (?,?)";
		int update = jt.update(sql, guest.getFirstName(), guest.getLastName());
		return update;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
	@Cacheable(value="guest", condition="#id>0")
	public Guest findById(Long id) {
		String sql = "SELECT firstname, lastname FROM guests WHERE id =?";
		RowMapper<Guest> rowMapper = new RowMapper<Guest>() {

			@Override
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new GuestImpl(rs.getString("firstname"),
						rs.getString("lastname"));
			}

		};
		return this.jt.query(sql, new Object[] { id }, rowMapper).get(0);
	}

	@Override
	public List<Guest> findByName(String name) {
		String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE lastname=?";
		Object[] args = new Object[] { name };
		List<Guest> list = this.jt.query(sql, createRowMapper(), args);
		return list;
	}

	@Override
	public List<Guest> findAll() {
		String sql = "SELECT id, firstname, lastname FROM GUESTS";
		Object[] args = new Object[] {  };
		List<Guest> list = this.jt.query(sql, createRowMapper(), args);
		return list;
	}

	@Override
	public Guest update(Guest guest) {
		if (guest.getId() == null) {
			this.create(guest);
		}
		String sql = "UPDATE guests SET  firstname = ?, lastname=? WHERE id = ?";
		Object[] args = new Object[] { guest.getFirstName(), guest.getLastName(), guest.getId() };
		int updatedRows = this.jt.update(sql, args);
		return guest;
	}

	@Override
	public void remove(Guest guest) {
		String sql = "DELETE FROM GUESTS WHERE id = ?";
		Object[] args = new Object[] { guest.getId() };
		int updatedRows = this.jt.update(sql, args);
	}
	
	private RowMapper<Guest> createRowMapper() {
		return new RowMapper<Guest>() {
			@Override
			public Guest mapRow(ResultSet rs, int row) throws SQLException {
				return new GuestImpl(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"));
			}
		};
	}

}
