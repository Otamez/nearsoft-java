package com.shipping.backend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ShippingServiceController {

    private final static Logger log = LoggerFactory.getLogger(ShippingServiceController.class);

    @Value("${queue.routing.key}")
    private String routingKey;

    @Value("${queue.topic.name}")
    private String topicExchange;

    @Value("${queue.name}")
    private String queueName;

    private RabbitTemplate rabbitTemplate;

    public  ShippingServiceController( final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate =rabbitTemplate;
    }

    @GetMapping("/getTypes")
    public void getTypes() {
        try{
        Map<String, Object> params = new HashMap<>();
        params.put("type","packageType");
        String payload = new ObjectMapper().writeValueAsString(params);
        rabbitTemplate.setDefaultReceiveQueue(queueName);
        String response = (String) rabbitTemplate.convertSendAndReceive(topicExchange, routingKey, payload);
        log.info(topicExchange);
        log.info(routingKey);
        log.info(payload);
        log.info(response);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
