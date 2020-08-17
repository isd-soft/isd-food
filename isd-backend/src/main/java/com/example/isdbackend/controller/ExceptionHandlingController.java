package com.example.isdbackend.controller;

import com.example.isdbackend.dto.ErrorDTO;
import com.example.isdbackend.exception.OrderException;
import com.example.isdbackend.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorDTO> orderException(OrderException oe) {
        return new ResponseEntity<>(new ErrorDTO("orders", oe.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDTO> userException(UserException ue) {
        return new ResponseEntity<>(new ErrorDTO("users", ue.getMessage()), HttpStatus.OK);
    }
}
