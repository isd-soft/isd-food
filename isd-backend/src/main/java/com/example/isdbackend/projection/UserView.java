package com.example.isdbackend.projection;

import com.example.isdbackend.model.Role;

public interface UserView {
    Long getId();

    String getEmail();

    String getSkypeId();

    String getFirstName();

    String getLastName();

    String getEmploymentDate();

    Role getRole();

    boolean isEnabled();
}
