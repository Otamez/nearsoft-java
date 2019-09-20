package com.shipping.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price;

}
