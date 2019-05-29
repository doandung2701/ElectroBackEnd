package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
@Repository
@Transactional
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional(readOnly=true)
	public User findById(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUserEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByUserEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public User findByEmailAndPassword(String email,String password) {
		// TODO Auto-generated method stub
		return userRepo.findByEmailAndPassword(email, password);
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUsernameAndPassword(String username,String password) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameAndPassword(username, password);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	@Transactional(readOnly=true)
	public Long count() {
		// TODO Auto-generated method stub
		return userRepo.count();
	}

	@Override
	public User findByUsernameOrEmail(String usernameOrEmail) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameOrEmail(usernameOrEmail);
	}

}
