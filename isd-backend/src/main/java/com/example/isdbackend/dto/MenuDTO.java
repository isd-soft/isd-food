package com.example.isdbackend.dto;

import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;


@Data
@AllArgsConstructor
public class MenuDTO {
    private String name;
    private DayOfWeek dayOfWeek;
    private String image;
    private Provider provider;
    private List<MenuType> menuTypes;
}
