package com.eduardo.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.eduardo.cursomc.domain.PaymentWithBankSlip;
import com.eduardo.cursomc.domain.PaymentWithCreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            @Override
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PaymentWithCreditCard.class);
                objectMapper.registerSubtypes(PaymentWithBankSlip.class);
                super.configure(objectMapper);
            }
        };
    }
}