package com.example.isdbackend.service;

import com.example.isdbackend.model.Notification;
import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;

@Service

public class NotificationService extends AbstractServiceCrud {

    public NotificationService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
    }

    public void addNotification(Notification notification){

    }
}
