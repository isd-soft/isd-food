package com.example.isdbackend.controller;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping(path = "/hello")
    public String sayHello() {
        return "Leha is the best";
    }

   /* @RequestMapping("/menu")
    public List<Menu> getMenus(){
        Menu m = new Menu();
        List<Menu> menus = new ArrayList<>();
        menus = (List<Menu>) menuRepository.findAll();
        for (Menu item:
                menus) {
            m = item;
            System.out.println(item.getName());
        }
        return menus;
    }*/

    @RequestMapping("/menu")
    public List<Menu> getMenus(){
        List<Menu> menus = (List<Menu>)menuService.getAllMenus();
        return menus;
    }
}
