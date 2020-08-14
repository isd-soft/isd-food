package com.example.isdbackend.service;

import com.example.isdbackend.dto.OrderDTO;
import com.example.isdbackend.dto.converter.OrderConverter;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Properties;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.repository.*;
import com.example.isdbackend.validation.OrderValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OrderService extends AbstractServiceCrud {

    private final OrderConverter orderConverter;
    private final PropertiesRepository propertiesRepository;
    private final UserService userService;

    public OrderService(UserService userService, PropertiesRepository propertiesRepository, OrderConverter orderConverter, MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository);

        this.orderConverter = orderConverter;
        this.propertiesRepository = propertiesRepository;
        this.userService = userService;
    }

    public boolean areOrdersDisabled(Date orderOnDate) {
        Date lastOrderDate = propertiesRepository.findLastOrderDate();

        return (lastOrderDate == null || !lastOrderDate.before(orderOnDate));
    }

    public String canMakeOrder(OrderDTO orderDTO) {
        orderDTO.setUserId(userService.getCurrentUserId());

        int ordersCount = orderRepository.findOrdersCountByDateAndUserId(orderDTO.getDate(), orderDTO.getUserId());
        boolean menuDayEqualsOrderDay = orderRepository.findMenuDayEqualsOrderDay(orderDTO.getDate(), orderDTO.getMenuTypeId());

        if (ordersCount > 1) return "You already made 2 orders on this day";

        if (!menuDayEqualsOrderDay) return "This menu is not available on this day";

        OrderValidation orderValidation = new OrderValidation(orderDTO.getDate(), new Date());

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

    public void update(OrderDTO orderDTO) {
        orderDTO.setUserId(userService.getCurrentUserId());
        orderRepository.save(orderConverter.convertFromDto(orderDTO));
    }

    public void placeTheOrder(Date orderDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(orderDate);

        orderRepository.setOrdersAsPlaced(orderDate);
        Properties updatedProperties = new Properties();
        updatedProperties.setId("last_order");
        updatedProperties.setValue(strDate);
        updatedProperties.setType("string");

        propertiesRepository.save(updatedProperties);
    }

    public OrderFullView findOrderById(long id) {
        return orderRepository.findAllById(id);
    }

    public Page<OrderView> getOrders(Pageable pageable, OrderFilter orderFilter, Long userId) {
        return orderRepository.findAllBy(pageable, userId, orderFilter);
    }

}
