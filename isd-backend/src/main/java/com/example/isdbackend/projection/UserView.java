package com.example.isdbackend.projection;

public interface UserView {
    Long getId();

    String getEmail();

    String getSkypeId();

    String getFirstName();

    String getLastName();

    String getEmploymentDate();

    String getRoleName();

    boolean isEnabled();
}
