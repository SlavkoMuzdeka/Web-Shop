package com.example.projektni_ip.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender emailSender;

    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendPinEmail(String email, int pin) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("PIN for account activation");
        message.setText("Your PIN is " + pin + ". Enter this PIN to activate your account.");
        emailSender.send(message);
    }
}
