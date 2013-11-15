package net.gfu.seminar.spring.helloworld;

import java.util.List;

public interface GuestGenericDao extends GenericDao<Guest, Long> {
	public List<Guest> findByName(String name);
}
