package com.example.isdbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @JsonIgnore
    @ManyToMany
    private Set<MenuType> menuTypes;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuType> getMenuTypes() {
        return menuTypes;
    }

    public void setMenuTypes(Set<MenuType> menuTypes) {
        this.menuTypes = menuTypes;
    }
}
