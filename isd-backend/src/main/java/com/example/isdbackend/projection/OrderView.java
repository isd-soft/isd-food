package com.example.isdbackend.projection;

public interface OrderView {
    int getId();

    String getDate();

    boolean isOrdered();

    String getMenuName();

    String getMenuType();

    String getProviderImage();

    int getPrice();

    Integer getProviderId();
}
