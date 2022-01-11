package com.poc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findUserByName(String name);

	List<User> findUserBySurname(String surname);

	List<User> findUserByPincode(String pincode);

	@Query("update #{#entityName} e set e.deleted=true where e.id = ?1")
	@Transactional
	@Modifying
	void softDeleteById(long id);
	
	//@Query("select e from #{#entityName} e where e.id = ?1 and e.deleted=true")
	//@Query("select e from #{#entityName} e where e.id = ?1")
	@Query("select e from #{#entityName} e where e.deleted=true")
	@Transactional
	@Modifying
	//Boolean getSoftDeleted(long id);
	List<User> getSoftDeleted();

}
