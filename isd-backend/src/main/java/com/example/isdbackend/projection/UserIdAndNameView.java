package com.example.isdbackend.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserIdAndNameView {
    int getId();

    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
