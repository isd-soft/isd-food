package com.example.isdbackend.dto;

import com.example.isdbackend.model.NotificationSettings;
import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private char[] password;

    private NotificationSettings notificationSettings;

    private Date employmentDate;

    private String skypeId;

    private Boolean enabled;

}
