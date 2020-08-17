package com.example.isdbackend.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class NotificationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    private Boolean isEnable;

    private String date;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NotificationSettings() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public NotificationSettings(Long id, User user, Boolean isEnable) {
//        this.id = id;
//        this.user = user;
//        this.isEnable = isEnable;
//
//    }
}
