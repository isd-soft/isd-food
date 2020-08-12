package com.example.isdbackend.projection;

import java.util.Date;

public interface UserOrderView {
    Date getDate();

    int getUserId();

    int getProviderId();

    int getDeliveryPrice();

    int getPrice();
}
