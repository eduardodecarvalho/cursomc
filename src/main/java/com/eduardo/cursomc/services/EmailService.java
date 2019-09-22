package com.eduardo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.eduardo.cursomc.domain.Pedido;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage msg);
}
