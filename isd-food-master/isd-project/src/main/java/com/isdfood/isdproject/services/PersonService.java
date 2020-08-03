package com.isdfood.isdproject.services;

import com.isdfood.isdproject.repositories.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PersonService {
    private MenuItemsRepository menuItemsRepository;
    private MenuRepository menuRepository;
    private NotificationRepository notificationRepository;
    private OrdersRepository ordersRepository;
    private ProviderRepository providerRepository;
    private UserRepository userRepository;

    public PersonService(MenuItemsRepository menuItemsRepository, MenuRepository menuRepository, NotificationRepository notificationRepository, OrdersRepository ordersRepository, ProviderRepository providerRepository, UserRepository userRepository) {
        this.menuItemsRepository = menuItemsRepository;
        this.menuRepository = menuRepository;
        this.notificationRepository = notificationRepository;
        this.ordersRepository = ordersRepository;
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
    }

    public MenuItemsRepository getMenuItemsRepository() {
        return menuItemsRepository;
    }

    public void setMenuItemsRepository(MenuItemsRepository menuItemsRepository) {
        this.menuItemsRepository = menuItemsRepository;
    }

    public MenuRepository getMenuRepository() {
        return menuRepository;
    }

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public OrdersRepository getOrdersRepository() {
        return ordersRepository;
    }

    public void setOrdersRepository(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public ProviderRepository getProviderRepository() {
        return providerRepository;
    }

    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
