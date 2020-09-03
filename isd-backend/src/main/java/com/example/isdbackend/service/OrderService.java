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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public boolean areOrdersEnabled(Date orderOnDate) throws ParseException {
        Date lastOrderDate = propertiesRepository.findLastOrderDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return (lastOrderDate == null || lastOrderDate.before(sdf.parse(sdf.format(orderOnDate))));
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

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Order save(OrderDTO orderDTO) {
        return orderRepository.save(orderConverter.convertFromDto(orderDTO));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public String canUpdateOrder(OrderDTO orderDTO, long orderId) {
        orderDTO.setUserId(userService.getCurrentUserId());

        int ordersCount = orderRepository.findOrdersCountByDateAndOrderId(orderDTO.getDate(), orderId);
        boolean menuDayEqualsOrderDay = orderRepository.findMenuDayEqualsOrderDay(orderDTO.getDate(), orderDTO.getMenuTypeId());

        if (ordersCount > 1) return "You already made 2 orders on this day";

        if (!menuDayEqualsOrderDay) return "This menu is not available on this day";

        OrderValidation orderValidation = new OrderValidation(orderDTO.getDate(), DateUtil.getCurrentDate());

        String message = orderValidation.validate();

        return message;
    }

    public void delete(Order order) {
        orderRepository.delete(order);
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

    public String getLastOrderDate() {
        return DateUtil.getDateFromDateTime(propertiesRepository.findLastOrderDate());
    }


}
