package com.eduardo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.eduardo.cursomc.domain.Order;

public interface EmailService {

    void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage msg);
}
