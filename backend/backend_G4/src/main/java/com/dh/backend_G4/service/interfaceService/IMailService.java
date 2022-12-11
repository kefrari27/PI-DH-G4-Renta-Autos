package com.dh.backend_G4.service.interfaceService;

import javax.mail.MessagingException;

public interface IMailService {
    public void sendEmail(String to, String subject, String content);

    public void sendEmailWithImage(String name, String to, String subject, String content, String reserva) throws MessagingException;
    public void sendEmailBienvenida(String name, String to, String subject, String content) throws MessagingException;
}
