package com.isdfood.isdproject.model;

import com.isdfood.isdproject.model.abstractClasses.Person;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity

public class User extends Person {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date employmentDate;

    private boolean orderPermission;


    public boolean notificationActive;


    //Provider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    //Orders
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Orders> orders;

    //History
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Orders> history;

    //Notification
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Notification> notifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public boolean isOrderPermission() {
        return orderPermission;
    }

    public void setOrderPermission(boolean orderPermission) {
        this.orderPermission = orderPermission;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<Orders> getHistory() {
        return history;
    }

    public void setHistory(Set<Orders> history) {
        this.history = history;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public boolean isNotificationActive() {
        return notificationActive;
    }

    public void setNotificationActive(boolean notificationActive) {
        this.notificationActive = notificationActive;
    }
}