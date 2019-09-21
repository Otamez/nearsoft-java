package com.shipping.backend.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueueRequestMessage implements Serializable {

    private String type;

    @Override
    public String toString() {
        return "{\"type\":\"" + type + "\"}";
    }
}