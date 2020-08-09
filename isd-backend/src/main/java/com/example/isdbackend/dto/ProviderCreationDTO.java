package com.example.isdbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class ProviderCreationDTO {

    private String name;
    private Integer deliveryPrice;
    private String contactInfo;
    private String description;
    private URL imageURL;
    private Boolean isActive;
}
