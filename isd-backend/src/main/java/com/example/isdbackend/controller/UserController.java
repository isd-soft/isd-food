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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) throws UserException {
        if (userService.existsByEmail(user.getEmail()))
            throw new UserException("This email is already in use");

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrders(
            @PageableDefault(size = 20, sort = "date", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter) {

        Page<OrderView> orders = orderService.getOrders(pageable, orderFilter, userService.getCurrentUserId());

        if (!orderFilter.isOrdered()) {
            return new ResponseEntity<>(orders.get().collect(Collectors.toList()), HttpStatus.OK);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UserView>> getAllUsers(
            @PageableDefault(size = 20, sort = "employmentDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsersWithoutPage(){
        return userService.findAll();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.delete(userService.findUserById(userId));
    }

    /*@RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getInfoUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public void editUser(@RequestParam User user){
        userService.save(user);
    }*/

    @GetMapping("/getUser")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }

    @GetMapping("/{currentId}")
    public ResponseEntity<UserView> getInfoUser(@PathVariable Long currentId) {
        return new ResponseEntity<>(userService.findByIdUser(currentId), HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser2() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PutMapping("/edit/{currentId}")
    public void editUser(@PathVariable Long currentId,@RequestParam String firstName,@RequestParam String lastName,
                         @RequestParam String skypeId,@RequestParam String email,@RequestParam Boolean enable, @RequestParam String data) throws ParseException {
        System.out.println(data);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateForChange = sdf1.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(dateForChange.getTime());
        userService.EditUserInfo(currentId, firstName, lastName, skypeId, email, enable, sqlDate);
    }

    @PutMapping("/edit/password/{currentId}")
    @ResponseBody
    public String editUser(@PathVariable Long currentId, @RequestParam String password) {
        userService.changePass(currentId, password);
        return "Success";
    }

    @PostMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String email) throws UserException {
        if (!userService.existsByEmail(email))
            throw new UserException("This email is not used");

        userService.resetPassword(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<?> getCurrentUserRole() {
        return new ResponseEntity<>(userService.getCurrentUserRole(), HttpStatus.OK);
    }
}
