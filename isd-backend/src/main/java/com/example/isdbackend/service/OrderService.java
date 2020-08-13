package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.User;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.projection.OrderFullView;
import com.example.isdbackend.projection.OrderView;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractServiceCrud {

    public OrderService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository);
    }

    public OrderFullView findOrderById(long id) {
        return orderRepository.findAllById(id);
    }


    public void createOrder(Long user_id, Long menu_id){
        User user = userRepository.findById(user_id).orElseThrow();
        Menu menu = menuRepository.findById(menu_id).orElseThrow();
        orderRepository.save(new Order(user, menu));
    }

    public Page<OrderView> getOrders(Pageable pageable, OrderFilter orderFilter, Long userId) {
        return orderRepository.findAllBy(pageable, userId, orderFilter);
    }
}
