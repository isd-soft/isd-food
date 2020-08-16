package com.example.isdbackend.controller;


import com.example.isdbackend.exception.UserException;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.service.OrderService;
import com.example.isdbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) throws UserException {
        if (userService.existsByEmail(user.getEmail()))
            throw new UserException("This email is already in use");

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter) {

        Page<OrderView> orders = orderService.getOrders(pageable, orderFilter, userService.getCurrentUserId());

        if (!orderFilter.isOrdered()) {
            return new ResponseEntity<>(orders.get().collect(Collectors.toList()), HttpStatus.OK);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
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
    public ResponseEntity<UserView> getInfoUser(@PathVariable Long currentId) {
        return new ResponseEntity<>(userService.findByIdUser(currentId), HttpStatus.OK);
    }

    @PutMapping("/edit/{currentId}")
    @ResponseBody
    public String editUser(@PathVariable Long currentId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String skypeId, @RequestParam String email) {
        userService.EditUserInfo(currentId, firstName, lastName, skypeId, email);
        return "Success";
    }

    @PutMapping("/edit/password/{currentId}")
    @ResponseBody
    public String editUser(@PathVariable Long currentId, @RequestParam String password) {
        userService.changePass(currentId, password);
        return "Success";
    }

}
