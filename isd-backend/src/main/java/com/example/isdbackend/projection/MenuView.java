package com.example.isdbackend.projection;

public interface MenuView {
    int getId();

    String getName();

    String getDayOfWeek();

    String getImage();

    boolean isActive();

    ProviderView getProvider();
}
