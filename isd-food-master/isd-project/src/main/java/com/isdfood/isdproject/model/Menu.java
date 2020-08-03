package com.isdfood.isdproject.model;

import com.isdfood.isdproject.model.enums.Days;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Menu {
    public Menu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Provider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    //Orders
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    //MenuItems
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "menu")
    private Set<MenuItems> menuItems;

    //MenuDay
    @Enumerated(value = EnumType.STRING)
    private Days day;

    private String menuName;
    private Float price;
    private boolean active;

    @Lob
    private Byte[] img;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Set<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
