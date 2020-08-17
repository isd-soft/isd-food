package com.example.isdbackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Properties {

    @Id
    private String id;

    private String value;

    private String type;

}
