package com.example.isdbackend.controller;

import com.example.isdbackend.dto.UserPaymentData;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.service.SupervisorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    private final SupervisorService supervisorService;

    @GetMapping("/payment")
    public ResponseEntity<List<UserPaymentData>> getAllPaymentData(OrderFilter orderFilter) {
        return new ResponseEntity<>(supervisorService.getAllPaymentData(orderFilter), HttpStatus.OK);
    }

}
