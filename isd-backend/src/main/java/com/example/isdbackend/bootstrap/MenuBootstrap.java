package com.example.isdbackend.bootstrap;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.MenuTypeRepository;
import com.example.isdbackend.repository.ProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MenuBootstrap implements CommandLineRunner {
    private final MenuRepository menuRepository;
    private final ProviderRepository providerRepository;
    private final MenuTypeRepository menuTypeRepository;

    public MenuBootstrap(MenuRepository menuRepository, ProviderRepository providerRepository, MenuTypeRepository menuTypeRepository) {
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.menuTypeRepository = menuTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /* MenuType S = new MenuType();
        S.setPrice(100f);
        S.setType('S');
        menuTypeRepository.save(S);*/


        Provider andys = new Provider();
        andys.setName("Andys pizza");
        andys.setDeliveryPrice(40);
        providerRepository.save(andys);

        Menu salat = new Menu();
        salat.setName("Cezar");
        salat.setProvider(andys);
        menuRepository.save(salat);

        Menu salat2 = new Menu();
        salat2.setName("Olivie");
        salat2.setProvider(andys);
        menuRepository.save(salat2);
    }
}
