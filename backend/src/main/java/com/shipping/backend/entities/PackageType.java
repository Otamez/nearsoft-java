package com.shipping.backend.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price;

}
