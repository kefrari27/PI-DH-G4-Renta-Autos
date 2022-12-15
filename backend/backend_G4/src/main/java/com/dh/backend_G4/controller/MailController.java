package com.dh.backend_G4.controller;

import com.dh.backend_G4.service.interfaceService.IMailService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("api/v1/mail")
public class MailController {

    public static Logger logger = Logger.getLogger(MailController.class);

    private final IMailService mailService;

    public MailController(IMailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public void sendMail(){
        String to = "mauricio.grisales.salazar@gmail.com";
        String subject = "Correo prueba 1";
        String content = "Nos alegra que nos hayas elegido para darte el mejor servicio, esperamos que disfrutes como te lo mereces";
        mailService.sendEmail(to,subject,content );
    }

    @GetMapping("/imagen")
    public void sendMailImagen() throws MessagingException {
        String name = "Mauricio";
        String to = "mauricio.grisales.salazar@gmail.com";
        String subject = "Reserva DigitalBooking4";
        String content = "Nos alegra que nos hayas elegido para darte el mejor servicio, esperamos que disfrutes como te lo mereces";
        String reserva = "Fecha Checkin = 2022-12-30, Fecha Checkout = 2023-01-05";
        mailService.sendEmailWithImage(name, to, subject, content, reserva);
    }
}
