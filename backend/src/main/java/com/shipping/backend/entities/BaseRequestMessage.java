package com.shipping.backend.entities;

import java.io.Serializable;

public class BaseRequestMessage implements Serializable {

    private String type;

    public BaseRequestMessage(){}

    public void setType(String type) {
        this.type = type;
    }

}
