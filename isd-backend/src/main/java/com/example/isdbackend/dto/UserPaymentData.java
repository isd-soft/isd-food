package com.example.isdbackend.dto;

public class UserPaymentData {

    private int userId;

    private double payment;

    private String fullName;

    public UserPaymentData() {
    }

    public UserPaymentData(int userId, double payment, String fullName) {
        this.userId = userId;
        this.payment = payment;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
