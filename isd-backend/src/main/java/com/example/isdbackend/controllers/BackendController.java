package com.example.isdbackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class BackendController {
    @RequestMapping(path = "/hello")
    public String sayHello() {
        return "Spartak is the champion";
    }
}
