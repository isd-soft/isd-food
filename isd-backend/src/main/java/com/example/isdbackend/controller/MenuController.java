package com.example.isdbackend.controller;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.projection.MenuView;
import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.repository.MenuTypeRepository;
import com.example.isdbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
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

    @GetMapping("/menu/day")
    public ResponseEntity<List<Menu>> getMenus(@RequestParam DayOfWeek day){
        return new ResponseEntity<>((List<Menu>)menuService.getMenusByDay(day), HttpStatus.OK);
    }

   /* @GetMapping("/menu/day")
    public ResponseEntity<List<MenuView>> getMenus(@RequestParam DayOfWeek day){
        return new ResponseEntity<>((List<MenuView>)menuService.getMenusByDay(day), HttpStatus.OK);
    }*/

}
