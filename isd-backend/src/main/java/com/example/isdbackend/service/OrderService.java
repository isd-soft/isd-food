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
        orderRepository.save(new Order(user, menuType));
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

}
