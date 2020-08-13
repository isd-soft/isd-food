package com.example.isdbackend.service;

import com.example.isdbackend.exception.IsOrderedException;
import com.example.isdbackend.model.*;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractServiceCrud {
    
    public UserService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository);
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

    public Iterable<Order> getHistory(Long id){ return userRepository.findById(id).orElseThrow().getOrders(); }


    public Iterable<Order> getCurrentOrders(Long id){
        return userRepository.findById(id).orElseThrow().getOrders().stream().filter(order -> order.isOrdered()).collect(Collectors.toList());
    }

    public Iterable<Menu> getProviderMenus(Integer providerId){ return providerRepository.findById(providerId).orElseThrow().getMenus(); }


    public void newOrder(Long id, Order order){
       User user = userRepository.findById(id).orElseThrow();
       user.getOrders().add(order);
       userRepository.save(user);
    }

    public void orderUpload(Order orderUpload) throws IsOrderedException {
        if(!orderRepository.findById(orderUpload.getId()).orElseThrow().isOrdered())
            orderRepository.save(orderUpload);
        else
            throw new IsOrderedException();
    }

    public void delete(User user){
        userRepository.delete(user);
    }
}
