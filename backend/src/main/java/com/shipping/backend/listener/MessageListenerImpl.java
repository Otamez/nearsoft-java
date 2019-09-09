package com.shipping.backend.listener;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageListenerImpl implements MessageListener {

    public void onMessage(String message){
        System.out.println(new Date());
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Message Received: " + message);
        System.out.println(new Date());
    }

}
