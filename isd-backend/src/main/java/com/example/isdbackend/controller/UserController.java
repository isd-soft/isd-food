package com.example.isdbackend.controller;


import com.example.isdbackend.model.User;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.service.UserService;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private OrderRepository orderRepository;

    public UserController(UserService userService, OrderRepository orderRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/account/{id}",method = RequestMethod.GET)
    public User getInfoUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/account/edit/{id}", method = RequestMethod.POST)
    public void editUser(@RequestParam User user){
        userService.save(user);
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(User user){
        userService.save(user);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addDelete(User user){
        userService.delete(user);
    }
}
