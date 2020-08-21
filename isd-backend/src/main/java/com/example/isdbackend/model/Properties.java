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

    public Properties() {
    }
    public Properties(String id, String value, String type){
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
