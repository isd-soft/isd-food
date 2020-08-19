package com.example.isdbackend.dto;

public class UserPaymentData extends PaymentModel {

    private int userId;

    public UserPaymentData() {
    }

    public UserPaymentData(int userId, double payment, String fullName) {
        this.userId = userId;
        super.setPayment(payment);
        super.setFullName(fullName);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
