package com.example.isdbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.net.URL;

@Getter
@Setter
public class ProviderUpdateDTO {
    @Id
    private Integer id;

    private String name;
    private Integer deliveryPrice;
    private String contactInfo;
    private String description;
    private URL imageURL;
    private Boolean isActive;
}
