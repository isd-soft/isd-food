package com.example.isdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Date date;
    private long menuTypeId;
    private long userId;

}
