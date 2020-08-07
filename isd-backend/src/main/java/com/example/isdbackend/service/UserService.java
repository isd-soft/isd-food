package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.model.User;
import com.example.isdbackend.repository.UserRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ProviderService providerService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public void notification(Long id, Boolean active){
        User user = userRepository.findById(id).orElseThrow();
        user.setNotification(active);
        userRepository.save(user);
    }

    public void notificationSettings(Long id, Time timeToReceive, Timestamp dateToEnable, Timestamp dateToDisable){
        User user = userRepository.findById(id).orElseThrow();
        user.getNotificationSettings().setDateToDisable(dateToDisable);
        user.getNotificationSettings().setDateToEnable(dateToEnable);
        user.getNotificationSettings().setTimeToReceive(timeToReceive);
        userRepository.save(user);
    }

    public Iterable<Order> getAllHistory(Long id){
        return userRepository.findById(id).orElseThrow().getOrders();
    }

    public Iterable<Order> getCurrentOrders(Long id){
        return userRepository.findById(id).orElseThrow().getOrders().stream().filter(order -> order.isOrdered()).collect(Collectors.toList());
    }

    public Iterable<Menu> getProviderMenus(Integer providerId){
        return providerService.getProviderMenus(providerId);
    }

    public Iterable<Menu> filter(Integer providerId,Long userId){
        Set<Order> orders = userRepository.findById(userId).orElseThrow().getOrders();
        Set<Menu> menus = new HashSet<>();
        for (Order order : orders)
           menus = order.getMenus().stream().filter(menu -> menu.getProvider().getId() == providerId).collect(Collectors.toSet());
        return menus;
    }
    

}
