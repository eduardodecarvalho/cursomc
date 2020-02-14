package com.eduardo.cursomc.domain;

import com.eduardo.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("paymentWithCreditCard")
public class PaymentWithCreditCard extends Payment {

    private static final long serialVersionUID = 1L;

    private Integer numberOfInstallment;

    public PaymentWithCreditCard() {
    }

    public PaymentWithCreditCard(Integer id, PaymentStatus statePaymentStatus, Order order,
                                 Integer numberOfInstallment) {
        super(id, statePaymentStatus, order);
        this.setNumberOfInstallment(numberOfInstallment);
    }

    public Integer getNumberOfInstallment() {
        return numberOfInstallment;
    }

    public void setNumberOfInstallment(Integer numberOfInstallment) {
        this.numberOfInstallment = numberOfInstallment;
    }

}
