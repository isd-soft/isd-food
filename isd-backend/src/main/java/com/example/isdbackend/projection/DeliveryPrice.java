package com.example.isdbackend.projection;

import java.util.Date;

public interface DeliveryPrice {
    Date getDate();

    int getProviderId();

    int getDeliveryPrice();
}
