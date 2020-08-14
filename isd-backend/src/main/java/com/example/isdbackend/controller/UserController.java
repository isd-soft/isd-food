package com.example.isdbackend.controller;


import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*@RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getInfoUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public void editUser(@RequestParam User user){
        userService.save(user);
    }*/

    @GetMapping("/{currentId}")
    public ResponseEntity<UserView> getInfoUser(@PathVariable Long currentId){
        return new ResponseEntity<>(userService.findByIdUser(currentId), HttpStatus.OK);
    }

    @PutMapping("/edit/{currentId}")
    @ResponseBody
    public String editUser(@PathVariable Long currentId,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String skypeId,@RequestParam String email){
        userService.EditUserInfo(currentId, firstName, lastName, skypeId, email);
        return "Success";
    }

    @PutMapping("/edit/password/{currentId}")
    @ResponseBody
    public String editUser(@PathVariable Long currentId,@RequestParam char[] password){
        userService.changePass(currentId, password);
        return "Success";
    }

    @GetMapping("/str")
    @ResponseBody
    public String showRequest(@RequestParam String str){
        return str;
}    }
