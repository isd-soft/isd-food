package com.example.isdbackend.projection;

import java.util.Date;

public interface OrderFullView {
    int getId();

    Date getDate();

    boolean isOrdered();

    MenuTypeView getMenuType();
}
