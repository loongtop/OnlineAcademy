package com.gkhy.rbacservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * @Name: EmailService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-09
 * @Updated: 2022-09-09
 * @Version: 1.0
 **/
public interface EmailService {

    void sendCode(String email);
}
