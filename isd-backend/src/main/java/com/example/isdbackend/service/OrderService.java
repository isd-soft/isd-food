package com.example.isdbackend.service;

import com.example.isdbackend.exception.IsOrderedException;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public Iterable<Order> getHistory(Long id){
        return userService.findUserById(id).getOrders();
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    public void orderUpload(Order orderUpload) throws IsOrderedException {
        if(!orderRepository.findById(orderUpload.getId()).orElseThrow().isOrdered())
            orderRepository.save(orderUpload);
        else
            throw new IsOrderedException();
    }
    public void setIsOrdered(Long id, Boolean active){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrdered(active);
        orderRepository.save(order);
    }



}
