package com.example.isdbackend.controller;

import com.example.isdbackend.dto.FileResource;
import com.example.isdbackend.dto.PaymentDataDTO;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Payment;
import com.example.isdbackend.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<PaymentDataDTO> getAllPaymentData(OrderFilter orderFilter, @PageableDefault(size = 50, sort = "users.employment_date") Pageable pageable) {
        return new ResponseEntity<>(paymentService.getAllPaymentData(orderFilter, pageable, false), HttpStatus.OK);
    }

    @GetMapping("/monthly")
    public ResponseEntity<Page<Payment>> getMonthlyPaymentData(Integer month, Integer year,
                                                               @PageableDefault(size = 50, sort = "id") Pageable pageable) {
        return new ResponseEntity<>(paymentService.getMonthlyPaymentData(month, year, pageable, false), HttpStatus.OK);
    }

    @GetMapping(value = "/export",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> exportPayment(OrderFilter orderFilter, Integer month, Integer year) {
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

    @GetMapping("/months")
    public ResponseEntity<List<String>> getAvailableMonths() {
        return new ResponseEntity<>(paymentService.getAvailableMonths(), HttpStatus.OK);
    }

}
