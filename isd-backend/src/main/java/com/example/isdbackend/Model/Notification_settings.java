package com.example.isdbackend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Notification_settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relationship

    //Relationship
    private Time time_to_receive;
    private Timestamp date_to_enable;
    private Timestamp date_to_disable;

    public Notification_settings() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime_to_receive() {
        return time_to_receive;
    }

    public void setTime_to_receive(Time time_to_receive) {
        this.time_to_receive = time_to_receive;
    }

    public Timestamp getDate_to_enable() {
        return date_to_enable;
    }

    public void setDate_to_enable(Timestamp date_to_enable) {
        this.date_to_enable = date_to_enable;
    }

    public Timestamp getDate_to_disable() {
        return date_to_disable;
    }

    public void setDate_to_disable(Timestamp date_to_disable) {
        this.date_to_disable = date_to_disable;
    }
}
