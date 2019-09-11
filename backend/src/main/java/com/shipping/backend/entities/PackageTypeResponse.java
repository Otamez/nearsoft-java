package com.shipping.backend.entities;

import java.io.Serializable;

public class PackageTypeResponse implements Serializable {

    private Integer id;
    private String description;
    private Double price;

    public PackageTypeResponse(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




}
