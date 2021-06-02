package org.unibl.etf.pisio.mailservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.unibl.etf.pisio.mailservice.models.User;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private static final String SENDER_НАМЕ = "etfbl.pisio@gmail.com";
    private static final String WELCOME_SUBJECT = "Dobrodošli na Asset Management";
    private static final String WELCOME_TEXT = "Pozdrav {firstName}, Uspješno ste se registrovali na AM aplikaciju. Molimo sačekajte dok Vam administrator odobri vaš nalog.";

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Async
    public void sendWelcomeEmail(User user) {
        System.out.println("Send email for: " + user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER_НАМЕ);
        message.setTo(user.getEmail());
        message.setSubject(WELCOME_SUBJECT);
        message.setText(WELCOME_TEXT.replace("{firstName}", user.getFirstName()));
        emailSender.send(message);
    }
}
