package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService extends AbstractServiceCrud {
    public MenuService(MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(menuRepository, providerRepository, orderRepository, userRepository);
    }

    public Iterable<Menu> getAllMenus(){
        return menuRepository.findAll();
    }
    public Menu getMenuById(Long id){
        return menuRepository.findById(id).orElseThrow();
    }
    public void save(Menu menu){
        menuRepository.save(menu);
    }
    public void delete(Menu menu){
        menuRepository.delete(menu);
    }


}
