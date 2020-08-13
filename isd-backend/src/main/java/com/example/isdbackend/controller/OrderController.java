package com.example.isdbackend.controller;

import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.UserRepository;
import com.example.isdbackend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/user/{user_id}/{menu_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser (@PathVariable("user_id") Long user_id, @PathVariable("menu_id") Long menu_id) {
        orderService.createOrder(user_id, menu_id);
        System.out.println("Succesfull");
        return "Success";
    }
}
