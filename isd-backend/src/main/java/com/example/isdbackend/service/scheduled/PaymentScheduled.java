package com.example.isdbackend.service.scheduled;


import com.example.isdbackend.dto.PaymentDataDTO;
import com.example.isdbackend.dto.UserPaymentData;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Payment;
import com.example.isdbackend.repository.PaymentRepository;
import com.example.isdbackend.service.PaymentService;
import com.example.isdbackend.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@Component
public class PaymentScheduled {

    private final PaymentService paymentService;

    private final PaymentRepository paymentRepository;

    @Scheduled(cron = "0 0 0 1 1/1 *")
    public void savePaymentData() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, -1);
        dt = c.getTime();

        OrderFilter orderFilter = new OrderFilter();
        orderFilter.setDateFrom(DateUtil.getDateFromDateTime(dt));

        c.setTime(currentDate);
        c.add(Calendar.DAY_OF_MONTH, -1);
        currentDate = c.getTime();

        orderFilter.setDateTo(DateUtil.getDateFromDateTime(currentDate));

        PaymentDataDTO paymentData = paymentService.getAllPaymentData(orderFilter, null, false);

        for (UserPaymentData userPaymentData : paymentData.getUserPayments()) {
            Payment payment = new Payment();
            payment.setDate(dateFormat.parse(paymentData.getDateTo()));
            payment.setFullName(userPaymentData.getFullName());
            payment.setPayment(userPaymentData.getPayment());
            payment.setUserId(userPaymentData.getUserId());

            paymentRepository.save(payment);
        }

    }
}
