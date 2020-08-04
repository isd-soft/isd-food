package com.isdfood.isdproject.model;

import com.isdfood.isdproject.model.enums.Role;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class User{

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contact;

    private Boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date employmentDate;

    private Boolean orderPermission;

    //Provider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    //Orders
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    private Set<Orders> orders;

    @OneToMany
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

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getOrderPermission() {
        return orderPermission;
    }

    public void setOrderPermission(Boolean orderPermission) {
        this.orderPermission = orderPermission;
    }

    public Set<Orders> getHistory() {
        return history;
    }

    public void setHistory(Set<Orders> history) {
        this.history = history;
    }

}