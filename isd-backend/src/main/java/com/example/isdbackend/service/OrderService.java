package com.example.isdbackend.service;

import com.example.isdbackend.dto.OrderDTO;
import com.example.isdbackend.dto.converter.OrderConverter;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Properties;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.PropertiesRepository;
import com.example.isdbackend.util.DateUtil;
import com.example.isdbackend.validation.OrderValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    private final OrderConverter orderConverter;
    private final PropertiesRepository propertiesRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(UserService userService, PropertiesRepository propertiesRepository, OrderRepository orderRepository, OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
        this.propertiesRepository = propertiesRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public boolean areOrdersEnabled(Date orderOnDate) {
        Date lastOrderDate = propertiesRepository.findLastOrderDate();

        return (lastOrderDate == null || lastOrderDate.before(orderOnDate));
    }

    public String canMakeOrder(OrderDTO orderDTO) {
        orderDTO.setUserId(userService.getCurrentUserId());

        int ordersCount = orderRepository.findOrdersCountByDateAndUserId(orderDTO.getDate(), orderDTO.getUserId());
        boolean menuDayEqualsOrderDay = orderRepository.findMenuDayEqualsOrderDay(orderDTO.getDate(), orderDTO.getMenuTypeId());

        if (ordersCount > 1) return "You already made 2 orders on this day";

        if (!menuDayEqualsOrderDay) return "This menu is not available on this day";

        OrderValidation orderValidation = new OrderValidation(orderDTO.getDate(), DateUtil.getCurrentDate());

        String message = orderValidation.validate();

        return message;
    }

    public Order save(OrderDTO orderDTO) {
        return orderRepository.save(orderConverter.convertFromDto(orderDTO));
    }

    public String canUpdateOrder(OrderDTO orderDTO) {
        boolean menuDayEqualsOrderDay = orderRepository.findMenuDayEqualsOrderDay(orderDTO.getDate(), orderDTO.getMenuTypeId());
        if (!menuDayEqualsOrderDay) return "This menu is not available on this day";

        return null;
    }

    public String canDeleteOrder(long orderId) {
        OrderFullView order = orderRepository.findAllById(orderId);

        if (order == null) return "This order doesn't exist";

        if (order.isOrdered()) return "Can not delete the placed order";

        return null;
    }

    public void update(OrderDTO orderDTO, long orderId) {
        orderDTO.setUserId(userService.getCurrentUserId());
        Order order = orderConverter.convertFromDto(orderDTO);
        order.setId(orderId);

        orderRepository.save(order);
    }

    public void delete(long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void placeTheOrder(Date orderDate) {
        String strDate = DateUtil.getDateFromDateTime(orderDate);

        orderRepository.setOrdersAsPlaced(orderDate);
        Properties updatedProperties = new Properties();
        updatedProperties.setId("last_order");
        updatedProperties.setValue(strDate);
        updatedProperties.setType("date");

        propertiesRepository.save(updatedProperties);
    }

    public OrderFullView findOrderById(long id) {
        return orderRepository.findAllById(id);
    }

    public Page<OrderView> getOrders(Pageable pageable, OrderFilter orderFilter, Long userId) {
        return orderRepository.findAllBy(pageable, userId, orderFilter);
    }

}
