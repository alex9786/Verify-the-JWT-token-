


package com.example.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.authentication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
 
//	@Query(value=" selet * from user where name=:name and password=:password",nativeQuery = true)
	User findOneByNameAndPassword(String name, String password);

	
	
}
