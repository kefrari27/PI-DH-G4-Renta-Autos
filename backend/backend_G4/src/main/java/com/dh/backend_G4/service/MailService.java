package com.dh.backend_G4.service;

import com.dh.backend_G4.service.interfaceService.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);
    }

    @Override
    public void sendEmailWithImage(String name, String to, String subject, String content, String reserva) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        String contentEmail =
                "<b>Apreciad@ "+name+"</b>,"
                + "<br><i>"+content+"</i>"
                + "<br><br><b>Estos son tus datos de reserva: </b>"
                + "<br>"+reserva
                + "<br><br><b>Saludos,</b>"
                + "<br><br><img src='https://res.cloudinary.com/kefrari27/image/upload/v1670806685/digitalbooking/logo_header_ufgca3.png'/>";

        helper.setText(contentEmail, true);

        mailSender.send(message);
    }
    @Override
    public void sendEmailBienvenida(String name, String to, String subject, String content) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        String contentEmail =
                "<b>Apreciad@ "+name+"</b>,"
                        + "<br><i>"+content+"</i>"
                        + "<br><br><b>Saludos,</b>"
                        + "<br><br><img src='https://res.cloudinary.com/kefrari27/image/upload/v1670806685/digitalbooking/logo_header_ufgca3.png'/>";

        helper.setText(contentEmail, true);

        mailSender.send(message);
    }
}
