package com.shipping.backend.services;

import com.shipping.backend.entities.BaseRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShippingRequestSenderImpl implements  ShippingRequestSender{

    private final static Logger log = LoggerFactory.getLogger(ShippingRequestSenderImpl.class);

    private RabbitTemplate rabbitTemplate;

    public  ShippingRequestSenderImpl( final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate =rabbitTemplate;
    }

    @Override
    public String sendRequest(String message) {
        return rabbitTemplate.convertSendAndReceive(message).toString();
    }
}
