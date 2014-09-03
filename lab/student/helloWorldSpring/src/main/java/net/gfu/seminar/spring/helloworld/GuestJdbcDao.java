package net.gfu.seminar.spring.helloworld;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GuestJdbcDao extends JdbcDaoSupport implements GuestDao {
	@Override
	public Long create(Guest guest) {
		final String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
		final Object[] args = new Object[] { guest.getFirstName(),
				guest.getLastName() };
		int updatedRows = this.getJdbcTemplate().update(sql, args);
		return Long.valueOf(updatedRows);
	}

	@Override
	public Guest findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Guest> findByName(String name) {
		String sql = "SELECT firstname, lastname FROM guests where lastname = ?";
		RowMapper<Guest> rowMapper = new RowMapper<Guest>() {
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Guest(rs.getString("firstname"),
						rs.getString("lastname"));
			}
		};
		List<Guest> list = getJdbcTemplate().query(sql, rowMapper,
				new Object[] { name });
		return list;
	}

	@Override
	public List<Guest> findAll() {
		String sql = "SELECT firstname, lastname FROM guests";
		RowMapper<Guest> rowMapper = new RowMapper<Guest>() {
			public Guest mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Guest(rs.getString("firstname"),
						rs.getString("lastname"));
			}
		};
		List<Guest> list = getJdbcTemplate().query(sql, rowMapper);
		return list;
	}

	@Override
	public Guest update(Guest guest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Guest guest) {
		// TODO Auto-generated method stub

	}
}