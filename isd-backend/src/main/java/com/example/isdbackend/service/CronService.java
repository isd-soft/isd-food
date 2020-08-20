package com.example.isdbackend.service;

import com.example.isdbackend.model.CronNotification;
import com.example.isdbackend.repository.CronNotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class CronService {
    private CronNotificationRepository cronNotificationRepository;

    public CronService(CronNotificationRepository cronNotificationRepository) {
        this.cronNotificationRepository = cronNotificationRepository;
    }
    public CronNotification getCron(String id){
        return cronNotificationRepository.findById(id).orElseThrow();
    }
}
