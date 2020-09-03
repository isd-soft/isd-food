package com.example.isdbackend.controller;


import com.example.isdbackend.dto.UserPaymentData;
import com.example.isdbackend.exception.UserException;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.service.OrderService;
import com.example.isdbackend.service.PaymentService;
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

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
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
            @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter) {

        Page<OrderView> orders = orderService.getOrders(pageable, orderFilter, userService.getCurrentUserId());

        if (!orderFilter.isOrdered()) {
            return new ResponseEntity<>(orders.getContent(), HttpStatus.OK);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @PageableDefault(size = 20, sort = "employmentDate", direction = Sort.Direction.DESC) Pageable pageable,
            String name) {

        if (name == null)
            return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
        else
            return new ResponseEntity<>(userService.getByName(name), HttpStatus.OK);


    }

    @GetMapping("/allUsers")
    public List<User> getAllUsersWithoutPage() {
        return userService.findAll();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable Long userId) {
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
    public User getCurrentUser() {
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

    @PutMapping("/edit")
    public void editUser(@RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String skypeId, @RequestParam String email, @RequestParam Boolean enable, @RequestParam String data) throws ParseException {
        Long currentId = userService.getCurrentUserId();

        if (!data.equals("")) {
            System.out.println(data);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dateForChange = sdf1.parse(data);
            java.sql.Date sqlDate = new java.sql.Date(dateForChange.getTime());
            userService.EditUserInfo(currentId, firstName, lastName, skypeId, email, enable, sqlDate);
        } else {
            userService.EditUserInfo(currentId, firstName, lastName, skypeId, email, enable, null);
        }
    }

    @PutMapping("/editBySupervisor/{userId}")
    public void editUserBySupervisor(@PathVariable Long userId, @RequestParam String firstName,
                                     @RequestParam String lastName, @RequestParam String skypeId,
                                     @RequestParam String email, @RequestParam String role, @RequestParam Boolean enable,
                                     @RequestParam String data) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateForChange = sdf1.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(dateForChange.getTime());
        userService.EditUserInfoBySupervisor(userId, firstName, lastName, skypeId, email, role, enable, sqlDate);
    }


    @PutMapping("/edit/password")
    @ResponseBody
    public String editUser(@RequestParam String password) {

        userService.changePass(userService.getCurrentUserId(), password);
        return "Success";
    }

    @PostMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestParam String email) throws UserException {
        if (!userService.existsByEmail(email))
            throw new UserException("This email is not used");

        userService.resetPassword(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/payment")
    public ResponseEntity<UserPaymentData> getUserPaymentData(OrderFilter orderFilter) {
        return new ResponseEntity<>(paymentService.getUserPaymentData(orderFilter), HttpStatus.OK);
    }

    @GetMapping("/payment/monthly")
    public ResponseEntity<?> getMonthlyPaymentData(Integer month, Integer year) {
        return new ResponseEntity<>(paymentService.getUserMonthlyPaymentData(month, year), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchUsers(@RequestParam String keywords) {
        return new ResponseEntity<>(this.userService.search(keywords), HttpStatus.OK);
    }

}
