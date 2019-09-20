package com.shipping.backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter  @Setter  @NoArgsConstructor
public class QueueRequestMessage implements Serializable {

    private String type;

}
