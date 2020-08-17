package com.example.isdbackend.service;

import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrderService extends AbstractServiceCrud {

    public OrderService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public OrderFullView findOrderById(long id) {
        return orderRepository.findAllById(id);
    }

    public Page<OrderView> getOrders(Pageable pageable, OrderFilter orderFilter, Long userId) {
        return orderRepository.findAllBy(pageable, userId, orderFilter);
    }

    public void createOrder(Long user_id, Long menuType_id){
        User user = userRepository.findById(user_id).orElseThrow();
        MenuType menuType = menuTypeRepository.findById(menuType_id).orElseThrow();
        Order order =new Order(user, menuType);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();


        order.setDate(new Date(dtf.format(localDate)));

        order.setOrdered(false);
        orderRepository.save(order);
    }
    public Order findById(Long id){
        return orderRepository.findById(id).orElseThrow();
    }
    public void delete(Order order){
        orderRepository.delete(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

}
