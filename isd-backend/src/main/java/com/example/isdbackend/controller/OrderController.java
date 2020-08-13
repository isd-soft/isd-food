package com.example.isdbackend.controller;

import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Order;
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
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
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

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderFullView> updateOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        if (savedOrder != null)
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(path = "/order/{user_id}/{menuType_id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser (@PathVariable("user_id") Long user_id, @PathVariable("menuType_id") Long menu_id) {
        orderService.createOrder(user_id, menu_id);
        System.out.println("Succesfull");
        return "Success";
    }

}
