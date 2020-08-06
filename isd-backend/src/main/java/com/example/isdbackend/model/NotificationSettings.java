package com.example.isdbackend.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class NotificationSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Time timeToReceive;
    private Timestamp dateToEnable;
    private Timestamp dateToDisable;

    public NotificationSettings() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTimeToReceive() {
        return timeToReceive;
    }

    public void setTimeToReceive(Time timeToReceive) {
        this.timeToReceive = timeToReceive;
    }

    public Timestamp getDateToEnable() {
        return dateToEnable;
    }

    public void setDateToEnable(Timestamp dateToEnable) {
        this.dateToEnable = dateToEnable;
    }

    public Timestamp getDateToDisable() {
        return dateToDisable;
    }

    public void setDateToDisable(Timestamp dateToDisable) {
        this.dateToDisable = dateToDisable;
    }
}
