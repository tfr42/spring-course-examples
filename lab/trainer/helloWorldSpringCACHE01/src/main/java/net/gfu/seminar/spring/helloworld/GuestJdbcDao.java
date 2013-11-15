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
	@Cacheable(value="guest")
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Guest> findAll() {
		// TODO Auto-generated method stub
		return null;
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
