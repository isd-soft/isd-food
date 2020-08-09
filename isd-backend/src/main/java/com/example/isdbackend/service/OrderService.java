package com.example.isdbackend.service;

import com.example.isdbackend.model.Order;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractServiceCrud {
    public OrderService(MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(menuRepository, providerRepository, orderRepository, userRepository);
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow();
    }


}
