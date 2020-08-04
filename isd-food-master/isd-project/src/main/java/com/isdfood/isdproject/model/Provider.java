package com.isdfood.isdproject.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Provider{
    public Provider() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float deliveryPrice;
    private String contactInfo;
    private String description;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider")
    private Set<Orders> orders;

    @Lob
    private Byte[] img;

    @ManyToMany(mappedBy = "providers")
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

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
