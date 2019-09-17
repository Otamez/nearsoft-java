package com.shipping.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippingQueueImpl implements ShippingQueue {

    private final static Logger log = LoggerFactory.getLogger(ShippingQueueImpl.class);

    private RabbitTemplate rabbitTemplate;

    public ShippingQueueImpl(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String sendRequest(String message) {
        log.info("Message send to the queue {}", message);
        String response = (String) rabbitTemplate.convertSendAndReceive(message);
        log.info("Message received from the queue {}", response);
        return response;
    }
}
