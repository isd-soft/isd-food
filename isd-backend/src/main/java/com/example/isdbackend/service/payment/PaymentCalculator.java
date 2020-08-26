package com.example.isdbackend.service.payment;

import com.example.isdbackend.projection.UserOrderView;

public class PaymentCalculator {

    protected double paymentCalc(double deliveryPrice, UserOrderView userOrder) {
        return userOrder.getPrice() * 0.5 + deliveryPrice;
    }

    protected double paymentCalc2(double deliveryPrice, UserOrderView userOrder, UserOrderView nextUserOrder) {
        double price = (Math.max(userOrder.getPrice(), nextUserOrder.getPrice()) * 0.5) + Math.min(userOrder.getPrice(), nextUserOrder.getPrice());

        return price + deliveryPrice;
    }
}
