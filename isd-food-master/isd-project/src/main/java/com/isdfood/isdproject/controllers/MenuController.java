package com.isdfood.isdproject.controllers;

import com.isdfood.isdproject.repositories.MenuRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    private MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @RequestMapping("/menu")
    public String getMenus(Model model){
        model.addAttribute("menus", menuRepository.findAll());
        return "menuList";
    }
}
