package com.example.isdbackend.controller;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.service.MenuService;
import org.springframework.web.bind.annotation.*;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.repository.MenuTypeRepository;
import com.example.isdbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public List<Menu> getMenus(){
        return (List<Menu>)menuService.getAllMenus();
    }
}
