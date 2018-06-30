package net.gfu.seminar.spring.helloworld;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class GuestJdbcSimpleInsertDao extends GuestJdbcDao {

    private SimpleJdbcInsert insertGuest;

    public GuestJdbcSimpleInsertDao(DataSource dataSource) {
        super(dataSource);
    }

    @PostConstruct
    public void init() {
        this.insertGuest = new SimpleJdbcInsert(this.getDataSource()).withTableName("GUESTS").
                usingGeneratedKeyColumns("ID");
    }

    /**
     * SQL Insert which returns the generated primary key.
     *
     * @param guest instance to persist
     * @return the generated primary key
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long create(final Guest guest) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("firstname", guest.getFirstName());
        parameters.put("lastname", guest.getLastName());
        Number newId = insertGuest.executeAndReturnKey(parameters);
        ((GuestImpl) guest).setId(newId.longValue());
        return newId.longValue();
    }

    @Override
    protected RowMapper<Guest> createRowMapper() {
        BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<Guest>();
        rowMapper.setMappedClass(GuestImpl.class);
        return rowMapper;
    }
}
