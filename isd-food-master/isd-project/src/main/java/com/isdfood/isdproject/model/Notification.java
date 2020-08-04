package com.isdfood.isdproject.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
public class Notification {
    public Notification() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private DayOfWeek days;

    private Date time;

    private String description;

    private boolean isAvailable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DayOfWeek getDays() {
        return days;
    }

    public void setDays(DayOfWeek days) {
        this.days = days;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
