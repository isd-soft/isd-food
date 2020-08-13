package com.example.isdbackend.controller;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.service.MenuService;
import org.springframework.web.bind.annotation.*;
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
