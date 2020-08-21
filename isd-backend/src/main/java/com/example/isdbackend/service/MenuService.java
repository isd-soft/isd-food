package com.example.isdbackend.service;

import com.example.isdbackend.dto.MenuDTO;
import com.example.isdbackend.dto.OrderDTO;
import com.example.isdbackend.dto.converter.MenuConverter;
import com.example.isdbackend.model.Menu;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.projection.MenuView;

import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;


@Service
public class MenuService extends AbstractServiceCrud {

   public ItemRepository itemRepository;
   public MenuConverter menuConverter;

    public MenuService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository, ItemRepository itemRepository, MenuConverter menuConverter) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
        this.itemRepository = itemRepository;
        this.menuConverter = menuConverter;
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

     public Iterable<Menu> getMenusByDay(DayOfWeek day){
        return menuRepository.findByDayOfWeek(day);
    }

    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public void addMenuType(MenuType menuType) {
        menuTypeRepository.save(menuType);
    }


    public Menu addFullMenu(MenuDTO menuDTO){
        Menu menu = menuRepository.save(menuConverter.convertFromDto(menuDTO));
        for (MenuType menuType: menuDTO.getMenuTypes()) {
            menuType.setMenu(menu);
            menuTypeRepository.save(menuType);
        }
        return menu;
    }

    public void deleteMenu(Long id) {
        menuRepository.delete(menuRepository.findById(id).orElseThrow());
    }
}
