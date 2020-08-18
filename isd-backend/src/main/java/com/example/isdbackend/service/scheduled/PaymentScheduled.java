package com.example.isdbackend.service.scheduled;


import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.service.SupervisorService;
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

    private final SupervisorService supervisorService;

    //    cron = "0 6 1 * *"
//    @Scheduled(fixedDelay = 2000)
//    public void savePaymentData() throws ParseException {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date dt = dateFormat.parse("2020-09-01");
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.MONTH, -1);
//        dt = c.getTime();
//
//        OrderFilter orderFilter = new OrderFilter();
//        orderFilter.setDateFrom(DateUtil.getDateFromDateTime(dt));
//
//        c.setTime(dt);
//        c.add(Calendar.DAY_OF_MONTH, -1);
//        dt = c.getTime();
//
//        orderFilter.setDateTo(DateUtil.getDateFromDateTime(dt));
//
//
//        supervisorService.getAllPaymentData(orderFilter);
//    }
}
