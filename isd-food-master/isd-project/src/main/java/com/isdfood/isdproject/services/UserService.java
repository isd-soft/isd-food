package com.isdfood.isdproject.services;

import com.isdfood.isdproject.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class UserService extends PersonService {

    public UserService(MenuItemsRepository menuItemsRepository, MenuRepository menuRepository, NotificationRepository notificationRepository, OrdersRepository ordersRepository, ProviderRepository providerRepository, UserRepository userRepository) {
        super(menuItemsRepository, menuRepository, notificationRepository, ordersRepository, providerRepository, userRepository);
    }
}
