package com.example.isdbackend.config;

import com.example.isdbackend.model.User;
import com.example.isdbackend.service.MailSender;
import com.example.isdbackend.service.UserService;
import com.example.isdbackend.util.DateUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Scheduled(cron = "0 0-5 7 * * ?\n", zone = "GMT+3.00")
    public void checkDate() {
        List<User> users = userService.findAll();
        String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (User user : users)
            if (!user.getNotificationSettings().isEnabled() && DateUtil.getDateFromDateTime(user.getNotificationSettings().getDateToEnable()).equals(currentDay) && user.getNotificationSettings().getDateToEnable() != null) {
                user.getNotificationSettings().setEnabled(true);
                user.getNotificationSettings().setDateToDisable(null);
            }

    }

    @Scheduled(cron = "0 30 9 ? * MON-FRI\n", zone = "GMT+3.00")
    public void scheduleNotification() {
        List<User> users = userService.findAll();
        for (User user : users)
            if (user.getNotificationSettings().isEnabled() && user.getEmail() != null)
                mailSender.sendSimpleMessage(user.getEmail(), "Time to eat", "Message");

    }


}
