package com.quoter.quoter.service.impl;

import com.quoter.quoter.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    @Override
    public void send(String to, String body){
        //send maik
    }
}
