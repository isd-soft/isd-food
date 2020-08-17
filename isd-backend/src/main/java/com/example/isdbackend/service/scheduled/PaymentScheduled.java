package com.example.isdbackend.service.scheduled;


import com.example.isdbackend.service.SupervisorService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentScheduled {

    private final SupervisorService supervisorService;

//    @Scheduled(cron = "0 6 1 * *")
//    public void savePaymentData() {
//
//    }
}
