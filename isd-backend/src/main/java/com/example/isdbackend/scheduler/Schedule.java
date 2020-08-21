package com.example.isdbackend.scheduler;

import com.example.isdbackend.model.User;
import com.example.isdbackend.service.MailSender;
import com.example.isdbackend.service.UserService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    public void checkUsers(){

    }






//    @Scheduled(cron = "0 0-5 7 * * ?\n",zone="GMT+3.00")
//    public void checkDate(){
//        List<User> users = userService.findAll();
//        String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//        for (User user : users)
//                if(!user.getNotificationSettings().getEnable() && user.getNotificationSettings().getDate().equals(currentDay) && user.getNotificationSettings().getDate() != null){
//                    user.getNotificationSettings().setEnable(true);
//                    user.getNotificationSettings().setDate(null);
//                    userService.save(user);
//                }
//
//
//    }


}
