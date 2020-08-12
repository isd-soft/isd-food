package com.example.isdbackend.bootstrap;

import com.example.isdbackend.model.Item;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.repository.MenuItemRepository;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.MenuTypeRepository;
import com.example.isdbackend.repository.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashSet;

@Component
public class MenuBootstrap implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final ProviderRepository providerRepository;
    private final MenuTypeRepository menuTypeRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuBootstrap(MenuRepository menuRepository, ProviderRepository providerRepository, MenuTypeRepository menuTypeRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.menuTypeRepository = menuTypeRepository;
        this.menuItemRepository = menuItemRepository;
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

    }
}
