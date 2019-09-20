package com.shipping.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
public class QueueRequestMessage implements Serializable {

    private String type;

}
