package com.example.isdbackend.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MenuType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private char type;

    @NotNull
    private Float price;

    @ManyToMany
    @JoinTable(name = "menu_item_type",
            joinColumns = @JoinColumn(name = "menu_type_id"), inverseJoinColumns = @JoinColumn(name = "item_id" ))
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public MenuType() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
