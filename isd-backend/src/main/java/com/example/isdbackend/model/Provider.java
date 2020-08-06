package com.example.isdbackend.model;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.net.URL;
import java.util.Set;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String contactInfo;
    private String description;
    private URL imageURL;
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    private Set<Menu> menus;

    public Provider() {
    }

    public Provider(String name, Long price, String contactInfo, String description) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.description = description;
        CurrencyUnit mdl = Monetary.getCurrency("MDL");
        //this.deliveryPrice = Monetary.getDefaultAmountFactory().setCurrency(mdl).setNumber(price).create();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public MonetaryAmount getDeliveryPrice() {
//        return deliveryPrice;
//    }

//    public void setDeliveryPrice(MonetaryAmount deliveryPrice) {
//        this.deliveryPrice = deliveryPrice;
//    }

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

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
