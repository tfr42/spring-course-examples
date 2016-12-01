package net.gfu.seminar.spring.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to access {@link Guest}s using Spring Data DSL.
 * 
 * @author tf
 *
 */
@Repository
@Transactional
public interface GuestRepository extends CrudRepository<Guest, Long> {

	Guest findById(Long key);

	@Query(value = "from Guest as g where g.lastName = :name")
	List<Guest> findByName(@Param("name") String name);

	List<Guest> findByFirstNameOrLastNameAllIgnoreCaseOrderByLastNameDesc(String firstName, String lastName);
}