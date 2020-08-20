package com.example.isdbackend.controller;

import com.example.isdbackend.dto.FileResource;
import com.example.isdbackend.dto.PaymentDataDTO;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<PaymentDataDTO> getAllPaymentData(OrderFilter orderFilter) {
        return new ResponseEntity<>(paymentService.getAllPaymentData(orderFilter, false), HttpStatus.OK);
    }

    @GetMapping("/monthly")
    public ResponseEntity<?> getMonthlyPaymentData(Integer month, Integer year) {
        return new ResponseEntity<>(paymentService.getMonthlyPaymentData(month, year, false), HttpStatus.OK);
    }

    @GetMapping(value = "/export",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> exportPayment(OrderFilter orderFilter, Integer month, Integer year) throws IOException {
        FileResource resource;

        if (month == null && year == null)
            resource = paymentService.exportPayment(orderFilter);
        else
            resource = paymentService.exportMonthlyPayment(month, year);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFileName() + "\"")
                .body(resource.getBytes());

    }
}
