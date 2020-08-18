package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.projection.MenuView;
import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuService extends AbstractServiceCrud {

    public MenuService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
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

   /* public Iterable<Menu> getMenusByDay(DayOfWeek day){
        return menuRepository.findByDayOfWeek(day);
    }*/

    public Iterable<Menu> getMenusByDay (DayOfWeek day){
        return menuRepository.findByDayOfWeek(day);
    }


}
