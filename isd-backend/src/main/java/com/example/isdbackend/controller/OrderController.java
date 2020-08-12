package com.example.isdbackend.controller;

import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<OrderView>> getOrders(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter,
            @PathVariable long userId) {

        return new ResponseEntity<>(orderService.getOrders(pageable, orderFilter, userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OrderView>> getAllOrders(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter) {

        return new ResponseEntity<>(orderService.getOrders(pageable, orderFilter, 0L), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderFullView> getOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }
}
