package net.gfu.seminar.spring.helloworld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
public class GuestJdbcDao extends JdbcDaoSupport implements GuestDao {

	protected static final Logger LOG = Logger.getLogger(GuestJdbcDao.class);

	public GuestJdbcDao(final DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	/**
	 * SQL Insert without returning the generated primary key.
	 * @param guest instance to persist
     * @return -1l as the generated primary key is not retrieved.
     */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Long create(final Guest guest) {
		final String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
		LOG.debug(sql);
		final Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows updated");
		((GuestImpl) guest).setId(-1l);
		return -1l;
	}

	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.SUPPORTS,readOnly=true)
	public Guest findById(final Long id) {
		Guest guest = null;
		final String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE id=?";
		LOG.debug(sql);
		Object[] args = new Object[] { id };
		List<Guest> list = this.getJdbcTemplate().query(sql, createRowMapper(), args);
		if (list.size() > 0) {
			guest = list.get(0);
		}
		return guest;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Guest> findByName(String name) {
		final String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE lastname=?";
		LOG.debug(sql);
		Object[] args = new Object[] { name };
		List<Guest> list = this.getJdbcTemplate().query(sql, createRowMapper(), args);
		return list;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Guest> findAll() {
		String sql = "SELECT id, firstname, lastname FROM guests";
		RowMapper<Guest> rowMapper = createRowMapper();
		return getJdbcTemplate().query(sql, rowMapper);
	}

	@Override
	public Guest update(final Guest guest) {
		if (guest.getId() == null) {
			this.create(guest);
		} else {
			final String sql = "UPDATE guests SET firstname = ?, lastname=? WHERE id = ?";
			LOG.debug(sql);
			Object[] args = new Object[] { guest.getFirstName(),
					guest.getLastName(), guest.getId() };
			int updatedRows = this.getJdbcTemplate().update(sql, args);
			LOG.debug(updatedRows + " rows updated");
		}
		return guest;
	}

	@Override
	public void delete(final Guest guest) {
		final String sql = "DELETE FROM GUESTS WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getId() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows deleted");
	}

	protected RowMapper<Guest> createRowMapper() {
		return (rs, rowNum) -> new GuestImpl(rs.getLong("id"),
                rs.getString("firstname"), rs.getString("lastname"));
	}

}