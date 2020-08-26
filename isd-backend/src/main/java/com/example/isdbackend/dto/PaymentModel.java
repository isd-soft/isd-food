package com.example.isdbackend.dto;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.math.RoundingMode;

@MappedSuperclass
public class PaymentModel {

    protected String fullName;

    protected double payment;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getPayment() {
        return BigDecimal.valueOf(payment)
                .setScale(1, RoundingMode.UP)
                .doubleValue();
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
