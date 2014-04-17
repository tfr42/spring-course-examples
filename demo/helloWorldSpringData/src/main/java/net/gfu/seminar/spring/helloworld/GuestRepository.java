package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	/**
	 * Finder by ID using Spring Data DSL.
	 * @param key the identifier
	 * @return a guest
	 */
	Guest findById(Long key);
	
	/**
	 * Finder by first or last name using Spring Data DSL.
	 * @param firstName a firstname
	 * @param lastName a lastname
	 * @return a list of guest matching either firstname or lastname
	 */
	List<Guest> findByFirstNameOrLastNameLike(String firstName, String lastName);

	/**
	 * Finder by name using custom query using the <code>@Query</code> annotation.
	 * @param name lastName of the guest
	 * @return a list of guests
	 */
	@Query(value = "from Guest as g where g.lastName = :name")
	List<Guest> findByName(@Param("name") String name);
}