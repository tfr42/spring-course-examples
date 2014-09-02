package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GuestMapper {

	@Insert("INSERT INTO GUESTS (firstname,lastname) VALUES (#{firstName},#{lastName})")
	@MapKey("id")
	Long create(@Param("guest") Guest guest);

	@Select("SELECT * FROM guests WHERE id = #{id}")
	Guest findById(@Param("id") Long id);

	@Select("SELECT * FROM guests WHERE lastname = #{name}")
	List<Guest> findByName(@Param("name") String name);

	@Select("SELECT * FROM guests")
	List<Guest> findAll();

	@Update("UPDATE guests SET firstname = #{firstName}, lastname=#{lastName} WHERE id = #{id}")
	Guest update(@Param("guest") Guest guest);

	@Delete("DELETE FROM GUESTS WHERE id = #{id}")
	void delete(@Param("id") Long id);
	
	@Select("SELECT count(id) from guests")
	long count();
}
