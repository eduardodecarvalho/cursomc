package com.eduardo.cursomc.domain;

import com.eduardo.cursomc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("paymentWithBankSlip")
public class PaymentWithBankSlip extends Payment {

    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payDay;

    public PaymentWithBankSlip() {
    }

    public PaymentWithBankSlip(Integer id, PaymentStatus estado, Order order,
                               Date dueDate, Date payDay) {
        super(id, estado, order);
        this.setPayDay(payDay);
        this.setDueDate(dueDate);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

}
