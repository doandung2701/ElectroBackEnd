package com.example.demo.service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleMailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendVerificationEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
	
}
