package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;


@Service
public class MenuService extends AbstractServiceCrud {

   public ItemRepository itemRepository;

    public MenuService(com.example.isdbackend.service.MailSender mailSender, com.example.isdbackend.repository.MenuRepository menuRepository, com.example.isdbackend.repository.ProviderRepository providerRepository, com.example.isdbackend.repository.OrderRepository orderRepository, com.example.isdbackend.repository.UserRepository userRepository, com.example.isdbackend.repository.MenuTypeRepository menuTypeRepository, ItemRepository itemRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
        this.itemRepository = itemRepository;
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

    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public void addMenuType(MenuType menuType) {
        menuTypeRepository.save(menuType);
    }

    public void addFullMenu(Menu menu, MenuType menuTypeS, MenuType menuTypeM) {
        Menu savedMenu = menuRepository.save(menu);
        menuTypeS.setMenu(savedMenu);
        menuTypeM.setMenu(savedMenu);
        menuTypeRepository.save(menuTypeS);
        menuTypeRepository.save(menuTypeM);
    }
}
