package net.gfu.seminar.spring.helloworld;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation based on Spring JdbcDaoSupport and JdbcTemplate.
 * 
 * @author tf
 *
 */
@Repository
public class GuestJdbcDao extends JdbcDaoSupport implements GuestDao {

	private static final Logger LOG = Logger.getLogger(GuestJdbcDao.class);

	@Override
	public int create(Guest guest) {
		String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows updated");
		return updatedRows;
	}

	@Override
	public Guest findById(Long id) {
		String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE id=?";
		LOG.debug(sql);
		Object[] args = new Object[] { id };
		List<Guest> list = this.getJdbcTemplate().query(sql,
				createRowMapper(), args);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Guest> findByName(String name) {
		String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE lastname=?";
		LOG.debug(sql);
		Object[] args = new Object[] { name };
		List<Guest> list = this.getJdbcTemplate().query(sql,
				createRowMapper(), args);
		return list;
	}

	@Override
	public List<Guest> findAll() {
		String sql = "SELECT id, firstname, lastname FROM GUESTS";
		LOG.debug(sql);
		Object[] args = new Object[] {};
		List<Guest> list = this.getJdbcTemplate().query(sql,
				createRowMapper(), args);
		return list;
	}

	@Override
	public Guest update(Guest guest) {
		if (guest.getId() == null) {
			this.create(guest);
		}
		String sql = "UPDATE guests SET  firstname = ?, lastname=? WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName(), guest.getId() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows updated");
		return guest;
	}

	@Override
	public void remove(Guest guest) {
		String sql = "DELETE FROM GUESTS WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getId() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows updated");
	}

	private RowMapper<Guest> createRowMapper() {

		return new RowMapper<Guest>() {
			@Override
			public Guest mapRow(ResultSet rs, int row) throws SQLException {
				return new GuestImpl(rs.getLong("id"),
						rs.getString("firstname"), rs.getString("lastname"));
			}
		};
	}

}
