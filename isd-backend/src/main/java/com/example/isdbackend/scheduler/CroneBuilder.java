package com.example.isdbackend.scheduler;


import com.example.isdbackend.service.CronService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CroneBuilder {

    private CronService cronService;

    public CroneBuilder(CronService cronService) {
        this.cronService = cronService;
    }

    @Bean(name = "firstNotification")
    public String getFirstTime(){
        return cronService.getCron("firstNotification","0 30 9 ? * MON-FRI").getValue();
    }
    @Bean(name = "secondNotification")
    public String getSecondTime(){
        return cronService.getCron("secondNotification","0 30 10 ? * MON-FRI").getValue();
    }
    @Bean(name = "userUpDate")
    public String getTimeToUpDateUser(){
        return cronService.getCron("userUpDate","0 0-5 7 * * ?").getValue();
    }

}
