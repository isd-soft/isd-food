package com.example.isdbackend.controller;


import com.example.isdbackend.exception.UserException;
import com.example.isdbackend.model.User;
import com.example.isdbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) throws UserException {
        if (userService.existsByEmail(user.getEmail()))
            throw new UserException("Thi email is already in use");

        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public String editUser(@PathVariable Long currentId,@RequestParam String password){
        userService.changePass(currentId, password);
        return "Success";
    }
