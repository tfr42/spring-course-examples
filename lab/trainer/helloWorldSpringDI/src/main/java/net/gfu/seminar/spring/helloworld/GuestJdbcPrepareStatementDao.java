package net.gfu.seminar.spring.helloworld;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class GuestJdbcPrepareStatementDao extends GuestJdbcDao {

    public GuestJdbcPrepareStatementDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * SQL Insert which returns the generated primary key.
     * @param guest instance to persist
     * @return the generated primary key
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Long create(final Guest guest) {
        final String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    new String[] { "ID" });
            ps.setString(1, guest.getFirstName());
            ps.setString(2, guest.getLastName());
            return ps;
        };
        int updatedRows = this.getJdbcTemplate().update(psc, keyHolder);
        LOG.debug(updatedRows + " rows updated");
        ((GuestImpl) guest).setId(keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }
}
