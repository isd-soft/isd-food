package com.example.isdbackend.controller;

import com.example.isdbackend.model.Order;

import com.example.isdbackend.service.OrderService;
import com.example.isdbackend.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {
    private final UserService userService;

    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/currentOrders/{id}", method = RequestMethod.GET)
    public List<Order> getCurrentOrders(@PathVariable Long id){
       return userService.getCurrentOrders(id);
    }

    @RequestMapping(value = "/allOrders/{id}",method = RequestMethod.GET)
    public Set<Order> getAllOrders(@PathVariable Long id){
        return userService.findUserById(id).getOrders();
    }

}
