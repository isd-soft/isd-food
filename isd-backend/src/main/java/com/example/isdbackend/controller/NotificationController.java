package com.example.isdbackend.controller;

import com.example.isdbackend.model.Notification;
import com.example.isdbackend.model.NotificationSettings;
import com.example.isdbackend.model.User;
import com.example.isdbackend.service.NotificationService;
import com.example.isdbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private  final UserService userService;
    private  final NotificationService notificationService;

    public NotificationController(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @RequestMapping(value = "/setSettingsActive/{id}",method = RequestMethod.POST)
    public void setUserNotification(@RequestParam Boolean active, @PathVariable Long id){
        User usr = userService.findUserById(id);
        usr.setNotification(active);
        userService.save(usr);
    }


    @RequestMapping(value = "/setNewSettings/{id}",method = RequestMethod.POST)
    public void setUserNotification(@RequestParam NotificationSettings notification, @PathVariable Long id){
        User usr = userService.findUserById(id);
        usr.setNotificationSettings(notification);
        userService.save(usr);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public List<Notification> getAllNotification(@PathVariable Long id){
        return notificationService.findNotifications(id);
    }

    @RequestMapping(value = "/newNotification/{id}",method = RequestMethod.POST)
    public void newNotification(@PathVariable Long id, Notification notification){
        User user = userService.findUserById(id);
        user.getNotificationList().add(notification);
        userService.save(user);
    }









}
