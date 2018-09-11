package net.gfu.seminar.spring.boot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "guest", path="guest")
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByLastName(String lastName);
}
