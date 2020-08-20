package com.example.isdbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IsdBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsdBackendApplication.class, args);
    }


}
