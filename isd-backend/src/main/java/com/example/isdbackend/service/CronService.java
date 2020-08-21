package com.example.isdbackend.service;

import com.example.isdbackend.model.Properties;
import com.example.isdbackend.repository.PropertiesRepository;
import org.springframework.stereotype.Service;

@Service
public class CronService {
    private final PropertiesRepository propertiesRepository;

    public CronService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    public void save(Properties properties){
        propertiesRepository.save(properties);
    }
    public Properties getCron(/*String id*/ String id, String value){
//        return propertiesRepository.findById(string).orElseThrow();
        return new Properties(id,value,"String");
    }
}
