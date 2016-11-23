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

@Repository
@Transactional
public class GuestJdbcDao extends JdbcDaoSupport implements GuestDao {

	private static final Logger LOG = Logger.getLogger(GuestJdbcDao.class);
	private SimpleJdbcInsert insertGuest;
	private String insertStrategy;

	public GuestJdbcDao() {
	}

	public GuestJdbcDao(final DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@PostConstruct
	public void init() {
		this.insertGuest = new SimpleJdbcInsert(this.getDataSource()).withTableName("GUESTS").
				usingGeneratedKeyColumns("ID");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int create(final Guest guest) {
		final String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
		LOG.debug(sql);
		int updatedRows = 0;

		// TODO choose one of the methods below to insert data, by setting either
		if (insertStrategy == null || insertStrategy.isEmpty() || insertStrategy.equalsIgnoreCase("jdbcinsert")) {
			updatedRows = this.createWithSimpleJdbcInsert(guest);
		} else if (insertStrategy.equalsIgnoreCase("simple")) {
			updatedRows = this.createWithSimpleStatement(sql, guest);
		} else if (insertStrategy.equalsIgnoreCase("psc")) {
			updatedRows = this.createWithPrepareStatementCreator(sql,guest);
		}
		
		return updatedRows;
	}

	/**
	 * SQL Insert without returning the generated primary key.
	 * @param sql insert statement
	 * @param guest instance to persist
     * @return number of effected rows
     */
	private int createWithSimpleStatement(final String sql, final Guest guest){
		final Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName() };
		return this.getJdbcTemplate().update(sql, args);
	}

	/**
	 * SQL Insert which returns the generated primary key.
	 * @param sql insert statement
	 * @param guest instance to persist
     * @return the generated primary key
     */
	private int createWithPrepareStatementCreator(final String sql, final Guest guest) {	
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "ID" });
				ps.setString(1, guest.getFirstName());
				ps.setString(2, guest.getLastName());
				return ps;
			}
		};
		int updatedRows = this.getJdbcTemplate().update(psc, keyHolder);
		LOG.debug(updatedRows + " rows updated");
		((GuestImpl) guest).setId(keyHolder.getKey().longValue());
		return keyHolder.getKey().intValue();
	}

	/**
	 * SQL Insert which returns the generated primary key.
	 * @param guest instance to persist
	 * @return the generated primary key
	 */
	private int createWithSimpleJdbcInsert(final Guest guest) {
		Map<String, Object> parameters = new HashMap<String, Object>(2);
		parameters.put("firstname", guest.getFirstName());
		parameters.put("lastname", guest.getLastName());
		Number newId = insertGuest.executeAndReturnKey(parameters);
		((GuestImpl) guest).setId(newId.longValue());
		return newId.intValue();
	}

	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.SUPPORTS,readOnly=true)
	public Guest findById(final Long id) {
		Guest guest = null;
		final String sql = "SELECT id, firstname, lastname FROM GUESTS WHERE id=?";
		LOG.debug(sql);
		Object[] args = new Object[] { id };
		List<Guest> list = this.getJdbcTemplate().query(sql, createRowMapper(),
				args);
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
		List<Guest> list = this.getJdbcTemplate().query(sql, createRowMapper(),
				args);
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
	public void remove(final Guest guest) {
		final String sql = "DELETE FROM GUESTS WHERE id = ?";
		LOG.debug(sql);
		Object[] args = new Object[] { guest.getId() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		LOG.debug(updatedRows + " rows updated");
	}

	private RowMapper<Guest> createRowMapper() {
		return new RowMapper<Guest>() {
			@Override
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new GuestImpl(rs.getLong("id"),
						rs.getString("firstname"), rs.getString("lastname"));
			}
		};
	}

	public String getInsertStrategy() {
		return insertStrategy;
	}

	public void setInsertStrategy(String insertStrategy) {
		this.insertStrategy = insertStrategy;
	}

}