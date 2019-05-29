package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email =:email")
	User findByUserEmail(@Param("email")String email);
	
	User findByUsername(String username);
	
	@Query("select u from User u where u.username=:username and u.password =:password")
	User findByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
	
	@Query("select u from User u where u.email=:email and u.password =:password")
	User findByEmailAndPassword(@Param("email")String email,@Param("password")String password);
	
	@Query("select u from User u where u.email=:usernameOrEmail or u.username=:usernameOrEmail")
	User findByUsernameOrEmail(@Param("usernameOrEmail")String usernameOrEmail);
}
