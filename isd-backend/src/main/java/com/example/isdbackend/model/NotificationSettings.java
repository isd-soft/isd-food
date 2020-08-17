package com.example.isdbackend.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class NotificationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean enabled;

    private Date dateToEnable;

    private Date dateToDisable;

    public NotificationSettings() {
    }

    public NotificationSettings(Long id, User user, boolean enabled) {
        this.id = id;
        this.user = user;
        this.enabled = enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDateToEnable() {
        return dateToEnable;
    }

    public void setDateToEnable(Date dateToEnable) {
        this.dateToEnable = dateToEnable;
    }

    public Date getDateToDisable() {
        return dateToDisable;
    }

    public void setDateToDisable(Date dateToDisable) {
        this.dateToDisable = dateToDisable;
    }
}
