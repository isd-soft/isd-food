package com.example.isdbackend.projection;

import java.util.Set;

public interface MenuView {
    int getId();

    String getName();

    String getDayOfWeek();

    String getImage();

    //boolean isActive();

    ProviderView getProvider();

    Set<MenuTypeView> getMenuTypes();
}
