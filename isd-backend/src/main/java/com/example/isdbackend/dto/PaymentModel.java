package com.example.isdbackend.dto;

import javax.persistence.MappedSuperclass;

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
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
