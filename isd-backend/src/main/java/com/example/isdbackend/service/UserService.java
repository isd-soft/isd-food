package com.example.isdbackend.service;

import com.example.isdbackend.exception.IsOrderedException;
import com.example.isdbackend.model.*;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProviderService providerService;
    private final OrderService orderService;


    public UserService(UserRepository userRepository, ProviderService providerService, OrderService orderService) {
        this.userRepository = userRepository;
        this.providerService = providerService;
        this.orderService = orderService;
    }


    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public void setNotificationSettings(Long id, Boolean active){
        User user = userRepository.findById(id).orElseThrow();
        user.setNotification(active);
        userRepository.save(user);
    }

    public void setNotificationSettings(Long id, NotificationSettings notificationSettings){
        User user = userRepository.findById(id).orElseThrow();
        user.setNotificationSettings(notificationSettings);
        userRepository.save(user);
    }

    public Iterable<Order> getHistory(Long id){
        return orderService.getHistory(id);
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
        for (Order order : orders) menus = order.getMenus().stream().filter(menu -> menu.getProvider().getId() == providerId).collect(Collectors.toSet());
        return menus;
    }

    public void newOrder(Long id, Order order){
       User user = userRepository.findById(id).orElseThrow();
       user.getOrders().add(order);
       userRepository.save(user);
    }

    public void orderUpload(Order orderUpload) throws IsOrderedException { orderService.orderUpload(orderUpload); }




}
