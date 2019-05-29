package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	User findById(Long id);
	
	List<User> findAll();
	
	User findByUserEmail(String email);
	
	User findByUsername(String username);
	
	User findByEmailAndPassword(String email,String password);
	
	User findByUsernameAndPassword(String username,String password);
	
	User findByUsernameOrEmail(String usernameOrEmail);
	
	User save(User user);
	
	Long count();
}
