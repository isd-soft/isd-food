package com.example.isdbackend.service.payment;

import com.example.isdbackend.dto.FileResource;
import com.example.isdbackend.dto.PaymentModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PaymentExporter {

    private final List<String> userFields = new ArrayList<>(Arrays.asList("Full name", "Payment"));

    public FileResource exportMonthly(List<? extends PaymentModel> payments, String period) {
        Excel excel = new Excel("Payment data");

        excel.createRow(1);

        excel.createCell(0, "Period", true);
        excel.createCell(1, period, true);

        excel.createRow(3);

        for (int i = 0; i < userFields.size(); i++) {
            excel.createCell(i, userFields.get(i), true);
        }

        for (int i = 0; i < payments.size(); i++) {
            excel.createRow(4 + i);
            excel.createCell(0, ((PaymentModel) payments.get(i)).getFullName());
            excel.createCell(1, String.valueOf(((PaymentModel) payments.get(i)).getPayment()));
        }

        FileResource fileResource = new FileResource();
        fileResource.setFileName("Payment_" + period + ".xlsx");

        fileResource.setBytes(excel.save());

        return fileResource;
    }

}
