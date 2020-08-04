package com.isdfood.isdproject.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;


@Entity
public class Menu {
    public Menu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Provider
    @ManyToMany
    @JoinTable(name = "menu_providers",
            joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "provider_id" ))
    private Set<Provider> providers;

    //Orders
    @ManyToOne
    private Orders orders;

    //MenuItems
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private Set<MenuItems> menuItems;

    private DayOfWeek day;
    private String menuName;
    private Float price;
    private Boolean isAvailable;

    @Lob
    private Byte[] img;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }

    public DayOfWeek getDay() { return day; }

    public void setDay(DayOfWeek day) {
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }
}
