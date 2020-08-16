package com.example.isdbackend.service;

import com.example.isdbackend.config.MailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Service
public class MailSender {
    private final MailConfig mailConfig;
    private final JavaMailSender javaMailSender;
    private final GeneratePassword generatePassword;

    public MailSender(MailConfig mailConfig, GeneratePassword generatePassword) {
        this.mailConfig = mailConfig;
        this.javaMailSender = mailConfig.getMailSender();
        this.generatePassword = generatePassword;
    }

    @Value("${spring.mail.username}")
    private String username;


    public void sendSimpleMessage(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + generatePassword.generatePassayPassword());
        javaMailSender.send(mailMessage);
    }

    public void sendHtmlMessage(String emailTo, String subject, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(username);
            helper.setTo(emailTo);
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlFromFile(String filePath, String pass) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        String link = "#", res, password;

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append(System.lineSeparator());
                line = br.readLine();
            }

            res = sb.toString();
            password = pass;
            res = res.replace("{{password}}", password).replace("{{link}}", link);
        }
        return res;
    }

}
