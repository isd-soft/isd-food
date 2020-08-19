package com.example.isdbackend.config;

import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.User;
import com.example.isdbackend.service.MailSender;
import com.example.isdbackend.service.OrderService;
import com.example.isdbackend.service.UserService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


@Component
@EnableScheduling
public class Schedule {

    private final OrderService orderService;
    private final UserService userService;
    private final MailSender mailSender;

    public Schedule(OrderService orderService, UserService userService, MailSender mailSender) {
        this.orderService = orderService;
        this.userService = userService;
        this.mailSender = mailSender;
    }

//    @Scheduled(cron = "0 0 23 ? * MON-FRI\n",zone="GMT+3.00")
//    public void scheduleNotification(){
//        List<Order> list = orderService.findAll();
//        Calendar calendar = Calendar.getInstance();
//        for (Order order : list) {
//            if(!order.isOrdered() && order.getDate().toString().equals(calendar.toString())){
//                orderService.delete(order);
//            }
//        }
//
//   }


//
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
//
//    @Scheduled(cron = "0 30 9 ? * MON-FRI\n",zone="GMT+3.00")
//    public void scheduleNotification(){
//        List<User> users = userService.findAll();
//        for (User user : users)
//            if (user.getNotificationSettings().getEnable() && user.getEmail() != null)
//                mailSender.sendSimpleMessage(user.getEmail(),"Time to eat","Message");
//
//   }
//

}
