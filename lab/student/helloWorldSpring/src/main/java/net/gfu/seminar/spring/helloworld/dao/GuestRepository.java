package net.gfu.seminar.spring.helloworld.dao;

import java.util.List;

import net.gfu.seminar.spring.helloworld.Guest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GuestRepository extends CrudRepository<Guest, Long> {
	
	Guest findById(Long key);
	
	List<Guest> findByFirstNameOrLastName(String firstname, String lastname);

	@Query(value = "from Guest as g where g.lastName = :name or g.firstName = :name")
	List<Guest> findByName(@Param("name") String name);

}
