package com.example.isdbackend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp date;

    private Boolean ordered;

    @OneToOne
    private Menu menu;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Optional<User> user, Optional<Menu> menu) {
    }

    public Order(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isOrdered() {
        return ordered;
    }

}
