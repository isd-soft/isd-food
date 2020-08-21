package com.example.isdbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDataDTO {

    private String dateFrom;
    private String dateTo;
    List<UserPaymentData> userPayments;
    private int totalPages;

}
