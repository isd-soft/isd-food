package com.example.isdbackend.controller;

import com.example.isdbackend.model.Order;

import com.example.isdbackend.service.OrderService;
import com.example.isdbackend.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;

    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/currentOrders/{id}", method = RequestMethod.GET)
    public List<Order> getCurrentOrders(@PathVariable Long id){
        Iterable<Order> orders = userService.getCurrentOrders(id);
        var ord = new ArrayList<Order>();
        orders.forEach(ord::add);
        return ord;
    }

    @RequestMapping(value = "/allOrders/{id}",method = RequestMethod.GET)
    public List<Order> getAllOrders(@PathVariable Long id){
        Iterable<Order> orders = userService.getHistory(id);
        var ord = new ArrayList<Order>();
        orders.forEach(ord::add);
        return ord;
    }

}
