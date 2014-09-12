package net.gfu.seminar.spring.helloworld;

import java.util.List;

import net.gfu.seminar.spring.helloworld.Guest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository to access {@link Guest}s using Spring Data DSL.
 * 
 * @author tf
 *
 */
@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

	Guest findById(Long key);

	@Query(value = "from Guest as g where g.lastName = :name")
	List<Guest> findByName(@Param("name") String name);
}