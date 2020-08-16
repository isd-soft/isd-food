package com.example.isdbackend.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserView {
        Long getId();

        String getEmail();

        String getSkypeId();

        String getFirstName();

        String getLastName();


}
