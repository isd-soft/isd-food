package com.example.isdbackend.controller;

import com.example.isdbackend.dto.MenuDTO;
import com.example.isdbackend.model.Item;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.repository.ItemRepository;
import com.example.isdbackend.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;


@RestController
public class MenuController {

    private MenuService menuService;
    private ItemRepository itemRepository;

    public MenuController(MenuService menuService, ItemRepository itemRepository) {
        this.menuService = menuService;
        this.itemRepository = itemRepository;
    }


    @GetMapping("/menu")
    public List<Menu> getMenus(){
        return (List<Menu>)menuService.getAllMenus();
    }

    @GetMapping("/menu/day")
    public ResponseEntity<List<Menu>> getMenus(@RequestParam DayOfWeek day){
        return new ResponseEntity<>((List<Menu>)menuService.getMenusByDay(day), HttpStatus.OK);
    }

    @GetMapping("/items")
    public  ResponseEntity<List<Item>> getItems(){
        return new ResponseEntity<>((List<Item>)itemRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping("/new_menu")
    public void addMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
    }

    @PostMapping("/new_item")
    public void addItem(@RequestParam String name) {
        Item item = new Item();
        item.setName(name);
        itemRepository.save(item);
    }

    @PostMapping("/new_menuType")
    public void addMenuType(@RequestBody MenuType menuType){
        menuService.addMenuType(menuType);
    }


    @PostMapping("/newMenuFull")
    public void addNewMenu(@RequestBody MenuDTO menu){
        menuService.addFullMenu(menu);
    }

    @DeleteMapping("menu/delete/{id}")
    public void deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);
    }

    @PutMapping("/updateMenu")
    public void updateMenu(@RequestBody Menu menu){
        menuService.updateMenu(menu);
    }




   /* @GetMapping("/menu/day")
    public ResponseEntity<List<MenuView>> getMenus(@RequestParam DayOfWeek day){
        return new ResponseEntity<>((List<MenuView>)menuService.getMenusByDay(day), HttpStatus.OK);
    }*/

}
