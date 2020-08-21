package com.example.isdbackend.scheduler;

import com.example.isdbackend.model.User;
import com.example.isdbackend.service.MailSender;
import com.example.isdbackend.service.UserService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Component
@EnableScheduling
public class Schedule {

    private final UserService userService;
    private final MailSender mailSender;

    public Schedule(UserService userService, MailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Scheduled(cron = "#{@firstNotification}")
    @Scheduled(cron = "#{@secondNotification}")
    public void sendFirstNotification(){
        List<User> users = userService.findAll();
        for (User user : users)
            if(user.isNotificationEnabled() && user.getEnabled())
                mailSender.sendHtmlMessage(user.getEmail(),"time to eat","eat");
    }


    @Scheduled(cron = "#{@userUpDate}")
    public void checkUsers() throws ParseException {
        LocalDate data = LocalDate.now();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateForChange = sdf1.parse(data.toString());
        java.sql.Date sqlDate = new java.sql.Date(dateForChange.getTime());

        List<User> users = userService.findAll();
        for (User usr : users)
            if(usr.getEnabled() && usr.getDateToChange().toString().equals(sqlDate.toString()) && !usr.isNotificationEnabled()){
                mailSender.sendHtmlMessage(usr.getEmail(),"isd","Your notification enabled");
                usr.setNotificationEnabled(true);
                usr.setDateToChange(null);
                userService.saveUser(usr);
            }
    }
}
