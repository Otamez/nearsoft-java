package com.shipping.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
public class PackageType implements Serializable {

    private int id;
    private String description;
    private int price;

    @Override
    public String toString() {
        return "[{\"id\":" + id
                + ",\"description\":\"" +  description
                +"\",\"price\":" + price + "}]";
    }

}
