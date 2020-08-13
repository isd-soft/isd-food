package com.example.isdbackend.projection;

import com.example.isdbackend.model.Item;

import java.util.Set;

public interface MenuTypeView {
    int getId();

    char getType();

    double getPrice();

    Set<Item> getItems();

    MenuView getMenu();
}
