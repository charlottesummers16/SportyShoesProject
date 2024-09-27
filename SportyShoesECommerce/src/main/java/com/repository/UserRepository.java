package com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("select u from User u where u.emailId = :emailId and u.password = :password")
	public List<User> loginUser(@Param("emailId") String emailId, @Param("password") String password);
	
}
