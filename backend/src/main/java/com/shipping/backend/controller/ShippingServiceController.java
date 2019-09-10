package com.shipping.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingServiceController {

    private final static Logger log = LoggerFactory.getLogger(ShippingServiceController.class);

    @Value("${queue.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange exchange;

    @GetMapping("/getTypes")
    public void getTypes() {
        String requestMessage = "{\"type\":\"packageSize\"}";
        String response = (String) rabbitTemplate.convertSendAndReceive(exchange.getName(), routingKey, requestMessage);
        log.info(response);
    }
}
