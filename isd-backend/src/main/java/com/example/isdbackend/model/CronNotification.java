package com.example.isdbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CronNotification {
    @Id
    private String id;

    private String firstNotificationCron;
    private String secondNotificationCron;

    private String timeToCheckUsers;

    private String ChuckNorris;

    public CronNotification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstNotificationCron() {
        return firstNotificationCron;
    }

    public void setFirstNotificationCron(String firstNotificationCron) {
        this.firstNotificationCron = firstNotificationCron;
    }

    public String getSecondNotificationCron() {
        return secondNotificationCron;
    }

    public void setSecondNotificationCron(String secondNotificationCron) {
        this.secondNotificationCron = secondNotificationCron;
    }

    public String getTimeToCheckUsers() {
        return timeToCheckUsers;
    }

    public void setTimeToCheckUsers(String timeToCheckUsers) {
        this.timeToCheckUsers = timeToCheckUsers;
    }

    public String getChuckNorris() {
        return ChuckNorris;
    }

    public void setChuckNorris(String chuckNorris) {
        ChuckNorris = chuckNorris;
    }
}
