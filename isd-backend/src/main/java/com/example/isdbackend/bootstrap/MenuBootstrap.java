package com.example.isdbackend.bootstrap;

import com.example.isdbackend.model.*;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.repository.*;
import com.example.isdbackend.service.GeneratePassword;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Component
public class MenuBootstrap implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final ProviderRepository providerRepository;
    private final MenuTypeRepository menuTypeRepository;
    private final MenuItemRepository menuItemRepository;
    private final GeneratePassword password;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public MenuBootstrap(MenuRepository menuRepository, ProviderRepository providerRepository, MenuTypeRepository menuTypeRepository, MenuItemRepository menuItemRepository, GeneratePassword password, UserRepository userRepository, OrderRepository orderRepository) {
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuItemRepository = menuItemRepository;
        this.password = password;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        MenuType M1 = new MenuType();
        M1.setPrice(50f);
        M1.setType('M');
        menuTypeRepository.save(M1);

        MenuType S1 = new MenuType();
        S1.setPrice(100f);
        S1.setType('S');
        menuTypeRepository.save(S1);

        MenuType M2 = new MenuType();
        M2.setPrice(50f);
        M2.setType('M');
        menuTypeRepository.save(M2);

        MenuType S2 = new MenuType();
        S2.setPrice(100f);
        S2.setType('S');
        menuTypeRepository.save(S2);

        Provider andys = new Provider();
        andys.setName("Andys pizza");
        andys.setDeliveryPrice(40);

        providerRepository.save(andys);

        Menu salat = new Menu();
        salat.setName("Cezar");
        salat.setProvider(andys);
        S1.setMenu(salat);
        M1.setMenu(salat);
        menuRepository.save(salat);
        menuTypeRepository.save(S1);
        menuTypeRepository.save(M1);

        Menu salat2 = new Menu();
        salat2.setName("Olivie");
        salat2.setProvider(andys);
        S2.setMenu(salat2);
        M2.setMenu(salat2);
        menuRepository.save(salat2);
        menuTypeRepository.save(S2);
        menuTypeRepository.save(M2);

        Item mamaliga = new Item();
        mamaliga.setName("Mamaliga");
        menuItemRepository.save(mamaliga);

        Item borsh = new Item();
        borsh.setName("Borsh");
        menuItemRepository.save(borsh);

        M1.setItems(new HashSet<>());
        M1.getItems().add(borsh);
        M1.getItems().add(mamaliga);
        menuTypeRepository.save(M1);

        S1.setItems(new HashSet<>());
        S1.getItems().add(mamaliga);
        menuTypeRepository.save(S1);

        M2.setItems(new HashSet<>());
        M2.getItems().add(mamaliga);
        menuTypeRepository.save(M2);

        S2.setItems(new HashSet<>());
        S2.getItems().add(mamaliga);
        menuTypeRepository.save(S2);


        User user = new User();
        user.setOrders(new HashSet<Order>());
        Order order = new Order();
        order.setMenus(new HashSet<>());
        order.getMenus().
        order.setUser(user);
        user.getOrders().add(order);


        userRepository.save(user);


    }
}
