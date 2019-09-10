package com.shipping.backend.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingServiceController {

    private final RabbitTemplate rabbitTemplate;
    static final String topicExchangeName = "db-exchange";
    static final String routingKey = "#";


    public ShippingServiceController(RabbitTemplate rabbitTemplate){
       this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getTypes")
    public String getTypes() {
        String queueMessage = "{\"type\":\"packageSize\"}";
        String types = (String) rabbitTemplate.convertSendAndReceive(topicExchangeName, routingKey, queueMessage);
        return types;
    }
}
