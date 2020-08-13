package com.example.isdbackend.service;

import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;



@Service
public abstract class AbstractServiceCrud {
    private final MailSender mailSender;
    public final MenuRepository menuRepository;
    public final ProviderRepository providerRepository;
    public final OrderRepository orderRepository;
    public final UserRepository userRepository;
    public final MenuTypeRepository menuTypeRepository;

    public AbstractServiceCrud(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository) {
        this.mailSender = mailSender;
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuTypeRepository = menuTypeRepository;
    }
}

