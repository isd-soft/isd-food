package com.example.isdbackend.projection;

public interface MenuView {
    int getId();

    String getName();

    int getDayOfWeek();

    String getImage();

    ProviderView getProvider();

}
