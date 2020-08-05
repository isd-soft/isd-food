package com.example.isdbackend.Model;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URL;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    //Error with hibernate
    //private MonetaryAmount deliveryPrice;
    private String contactInfo;
    private String description;
    private URL imageURL;
    private Boolean isActive;

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
