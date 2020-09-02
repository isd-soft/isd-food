package com.example.isdbackend.controller;

import com.example.isdbackend.dto.OrderDTO;
import com.example.isdbackend.exception.OrderException;
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

import java.text.ParseException;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderView>> getAllOrders(
            @PageableDefault(size = 50, sort = "date", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter,
            Long userId) {

        if (userId == null) userId = 0L;

        return new ResponseEntity<>(orderService.getOrders(pageable, orderFilter, userId), HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderView>> getAllOrders(
            @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter) {

        return new ResponseEntity<>(orderService.getOrders(pageable, orderFilter, 0L), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderFullView> getOrder(@PathVariable long orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @PutMapping("/confirm/{id}/{confirm}")
    public void confirmOrder(@PathVariable Long id, @PathVariable boolean confirm) {
        Order order = orderService.findById(id);
        order.setOrdered(confirm);
        orderService.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(orderService.findById(id));
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<OrderView>> getOrders(
            @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            OrderFilter orderFilter,
            @PathVariable long userId) {

        return new ResponseEntity<>(orderService.getOrders(pageable, orderFilter, userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO) throws OrderException, ParseException {

        if (!orderService.areOrdersEnabled(orderDTO.getDate()))
            throw new OrderException("Orders are already placed");

        String orderAvailableMessage = orderService.canMakeOrder(orderDTO);

        if (orderAvailableMessage != null)
            throw new OrderException(orderAvailableMessage);

        Order newOrder = orderService.save(orderDTO);
        if (newOrder != null)
            return new ResponseEntity<>(HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable long orderId, @RequestBody OrderDTO orderDTO) throws OrderException, ParseException {
        if (!orderService.areOrdersEnabled(orderDTO.getDate()))
            throw new OrderException("Orders are already placed");

        String orderAvailableMessage = orderService.canUpdateOrder(orderDTO, orderId);

        if (orderAvailableMessage != null)
            throw new OrderException(orderAvailableMessage);

        orderService.update(orderDTO, orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable long orderId) throws OrderException {
        String deleteOrderAvailableMessage = orderService.canDeleteOrder(orderId);

        if (deleteOrderAvailableMessage != null)
            throw new OrderException(deleteOrderAvailableMessage);

        orderService.delete(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeTheOrder() {
        orderService.placeTheOrder(new Date());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
