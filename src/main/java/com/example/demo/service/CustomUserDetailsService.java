package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder pe;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByUsernameOrEmail((username));
		System.out.println(user.getPassword());
		if (user==null) throw new UsernameNotFoundException("User not found with username or email : " + username);
		return UserPrincipal.createUser(user);
	}

	@Transactional
	public UserDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
		User user = userService.findById(id);
		if (user==null) throw new UsernameNotFoundException("User not found with id : " + id);
		return UserPrincipal.createUser(user);
	}
}
