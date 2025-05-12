package com.example.campus_connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    // private final JavaMailSender mailSender;

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("no-reply@campusconnect.com"); // change if needed
        emailSender.send(message);
    }

    // public EmailService(JavaMailSender mailSender) {
    //     this.mailSender = mailSender;
    // }

    // public void sendVerificationEmail(String email, String token) {
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(email);
    //     message.setSubject("Email Verification");
    //     message.setText("Please click the link below to verify your email:\n\n"
    //             + "http://yourdomain.com/api/auth/verify-email?token=" + token);

    //     mailSender.send(message);
    // }

    // public void sendVerificationEmail(String to, String subject, String text) throws MessagingException {
    //     MimeMessage message = emailSender.createMimeMessage();
    //     MimeMessageHelper helper = new MimeMessageHelper(message, true);

    //     helper.setTo(to);
    //     helper.setSubject(subject);
    //     helper.setText(text, true);

    //     emailSender.send(message);
    // }
}