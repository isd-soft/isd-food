package com.example.isdbackend.filter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderFilter {

    private String dateFrom = "0";
    private String dateTo = "0";
    private List<String> providers = new ArrayList<>();
    private boolean ordered = true;

}
