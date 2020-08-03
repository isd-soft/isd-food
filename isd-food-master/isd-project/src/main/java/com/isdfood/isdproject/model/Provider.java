package com.isdfood.isdproject.model;

import com.isdfood.isdproject.model.abstractClasses.Person;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Provider extends Person {
    public Provider() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float deliveryPrice;
    private String contactInfo;
    private String description;

    //Users
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
    private Set<Orders> orders;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
    private Set<Menu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Float deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
