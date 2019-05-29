package com.example.demo.service;

import com.example.demo.model.VerificationToken;

public interface VerificationTokenService {

	VerificationToken findByToken(String token);
	
}
