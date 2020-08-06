package com.example.isdbackend.service;

import com.example.isdbackend.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class MailSender {
    private MailConfig mailConfig;
    private JavaMailSender javaMailSender;

    public MailSender(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
        javaMailSender = mailConfig.getMailSender();
    }

    @Value("${spring.mail.username}")
    private String username;

    //generating password
    @Autowired
    private GeneratePassword generatePassword;


    public void send(String emailTo,String subject,String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + generatePassword.generatePassayPassword());
        javaMailSender.send(mailMessage);
    }

}
