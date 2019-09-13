package com.shipping.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShippingRequestSenderImpl implements  ShippingRequestSender{

    private final static Logger log = LoggerFactory.getLogger(ShippingRequestSenderImpl.class);

    @Value("${config-queue.name}")
    private String QUEUE_NAME;

    @Value("${config-queue.exchange}")
    private String QUEUE_EXCHANGE;

    @Value("${config-queue.routing-key}")
    private String QUEUE_ROUTING_KEY;

    private RabbitTemplate rabbitTemplate;

    public  ShippingRequestSenderImpl( final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String sendRequest(String message) {
        log.info("Message to send to the queue {}", message);
        String response = (String) rabbitTemplate.convertSendAndReceive(QUEUE_EXCHANGE, QUEUE_ROUTING_KEY, message);
        log.info(response);
        return response;
    }
}
