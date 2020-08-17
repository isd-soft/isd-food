package com.example.isdbackend.bootstrap;

import com.example.isdbackend.model.*;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.SimpleFormatter;

@Component
public class MenuBootstrap implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final ProviderRepository providerRepository;
    private final MenuTypeRepository menuTypeRepository;
    private final MenuItemRepository menuItemRepository;

    private final UserRepository userRepository;

    public MenuBootstrap(MenuRepository menuRepository, ProviderRepository providerRepository, MenuTypeRepository menuTypeRepository, MenuItemRepository menuItemRepository, UserRepository userRepository) {

        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuItemRepository = menuItemRepository;
        this.userRepository = userRepository;

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
        M2.setPrice(70f);
        M2.setType('M');
        menuTypeRepository.save(M2);

        MenuType S2 = new MenuType();
        S2.setPrice(90f);
        S2.setType('S');
        menuTypeRepository.save(S2);

        MenuType M3 = new MenuType();
        M3.setPrice(50f);
        M3.setType('M');
        menuTypeRepository.save(M3);

        MenuType S3 = new MenuType();
        S3.setPrice(100f);
        S3.setType('S');
        menuTypeRepository.save(S3);

        MenuType M4 = new MenuType();
        M4.setPrice(150f);
        M4.setType('M');
        menuTypeRepository.save(M4);

        MenuType S4 = new MenuType();
        S4.setPrice(120f);
        S4.setType('S');
        menuTypeRepository.save(S4);

        Provider andys = new Provider();
        andys.setName("Andys pizza");
        andys.setDeliveryPrice(40);

        providerRepository.save(andys);

        Provider mac = new Provider();
        mac.setName("Mac Donalds");
        mac.setDeliveryPrice(20);

        providerRepository.save(mac);

        Menu salat = new Menu();
        salat.setName("Bistro");
        salat.setProvider(andys);
        salat.setDayOfWeek(DayOfWeek.FRIDAY);
        S1.setMenu(salat);
        M1.setMenu(salat);
        menuRepository.save(salat);
        menuTypeRepository.save(S1);
        menuTypeRepository.save(M1);


        Menu salat2 = new Menu();
        salat2.setName("Indean");
        salat2.setProvider(andys);
        salat2.setDayOfWeek(DayOfWeek.WEDNESDAY);
        S2.setMenu(salat2);
        M2.setMenu(salat2);
        menuRepository.save(salat2);
        menuTypeRepository.save(S2);
        menuTypeRepository.save(M2);

        Menu salat3 = new Menu();
        salat3.setName("Japan");
        salat3.setProvider(andys);
        salat3.setDayOfWeek(DayOfWeek.THURSDAY);
        S3.setMenu(salat3);
        M3.setMenu(salat3);
        menuRepository.save(salat3);
        menuTypeRepository.save(S3);
        menuTypeRepository.save(M3);

        Menu salat4 = new Menu();
        salat4.setName("Moldavian");
        salat4.setProvider(andys);
        salat4.setDayOfWeek(DayOfWeek.TUESDAY);
        S4.setMenu(salat4);
        M4.setMenu(salat4);
        menuRepository.save(salat4);
        menuTypeRepository.save(S4);
        menuTypeRepository.save(M4);

        Item mamaliga = new Item();
        mamaliga.setName("Mamaliga");
        menuItemRepository.save(mamaliga);

        Item borsh = new Item();
        borsh.setName("Borsh");
        menuItemRepository.save(borsh);

        Item poncho = new Item();
        poncho.setName("Poncho");
        menuItemRepository.save(poncho);

        Item sashlic = new Item();
        sashlic.setName("Sashlic");
        menuItemRepository.save(sashlic);

        M1.setItems(new HashSet<>());
        M1.getItems().add(borsh);
        M1.getItems().add(mamaliga);
        menuTypeRepository.save(M1);

        S1.setItems(new HashSet<>());
        S1.getItems().add(mamaliga);
        menuTypeRepository.save(S1);

        M2.setItems(new HashSet<>());
        M2.getItems().add(mamaliga);
        M2.getItems().add(sashlic);
        menuTypeRepository.save(M2);

        S2.setItems(new HashSet<>());
        S2.getItems().add(mamaliga);
        menuTypeRepository.save(S2);

        M3.setItems(new HashSet<>());
        M3.getItems().add(mamaliga);
        M3.getItems().add(sashlic);
        M3.getItems().add(poncho);
        menuTypeRepository.save(M3);

        S3.setItems(new HashSet<>());
        S3.getItems().add(mamaliga);
        S3.getItems().add(sashlic);
        menuTypeRepository.save(S3);

        M4.setItems(new HashSet<>());
        M4.getItems().add(mamaliga);
        M4.getItems().add(sashlic);
        M4.getItems().add(poncho);
        menuTypeRepository.save(M4);

        S4.setItems(new HashSet<>());
        S4.getItems().add(mamaliga);
        S4.getItems().add(sashlic);
        menuTypeRepository.save(S4);


        User user = new User();
        user.setFirstName("Petea");

        user.setLastName("Jhons");
        user.setEmail("Spartak@gmail.com");
        user.setSkypeId("JhonesId");
        user.setPassword("$2y$12$Gytj7ahrI8suqFJxtYG2IevbkegKn.Zj3v2DMSOhROerDvAbLMoiy".toCharArray());
        user.setOrders(new HashSet<>());
        user.setNotificationEnabled(true);
        user.setEnabled(true);



        Order order = new Order();
        order.setMenuType(S3);
        user.getOrders().add(order);
        order.setUser(user);
        order.setOrdered(false);



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();

        Order order1 = new Order();
        order1.setMenuType(S3);
        user.getOrders().add(order1);
        order1.setUser(user);
        order1.setOrdered(false);
        order.setDate(new Date(dtf.format(localDate)));
        order1.setDate(new Date(dtf.format(localDate)));


        Provider provider = new Provider();
        provider.setName("Andys");
        salat3.setProvider(provider);
        provider.setMenus(new HashSet<>());
        provider.getMenus().add(salat3);

        userRepository.save(user);

    }
}
