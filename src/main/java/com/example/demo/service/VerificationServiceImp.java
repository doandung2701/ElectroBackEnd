package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.VerificationToken;
import com.example.demo.repo.VerificationTokenRepo;

@Service
@Repository
@Transactional
public class VerificationServiceImp implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepo tokenRepo;
	
	@Override
	@Transactional(readOnly=true)
	public VerificationToken findByToken(String token) {
		return tokenRepo.findByToken(token);
	}

}
