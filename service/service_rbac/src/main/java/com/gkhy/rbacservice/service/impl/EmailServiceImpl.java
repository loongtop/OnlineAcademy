package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Name: EmailServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-09
 * @Updated: 2022-09-09
 * @Version: 1.0
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendCode(String email) {

        String subject = "For account email verification!";
        String text = creatLinkForUser(email);
        sendByMail(email, subject, text);
    }

    public void sendByMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    private String creatLinkForUser(String email){
        return "Link for you " + email + " to click! Thank you for your!";
    }
}
