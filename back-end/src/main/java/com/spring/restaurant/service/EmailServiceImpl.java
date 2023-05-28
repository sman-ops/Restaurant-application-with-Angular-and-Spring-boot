package com.spring.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.spring.restaurant.dto.Mail;
import com.spring.restaurant.util.UserCode;


@Service
public class EmailServiceImpl implements EmailService {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	@Override
	@Async
	public void sendCodeByMail(Mail mail) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("slimen.ghnimi@etudiant-fst.utm.tn");
		simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject("Code Active");
        simpleMailMessage.setText(mail.getCode());
        javaMailSender.send(simpleMailMessage);
        
	
	}

}
