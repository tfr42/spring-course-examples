package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

	Guest findById(Long key);

	@Query(value = "from Guest as g where g.lastName = :name")
	List<Guest> findByName(@Param("name") String name);
}