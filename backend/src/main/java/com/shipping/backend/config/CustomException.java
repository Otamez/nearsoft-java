package com.shipping.backend.config;

public class CustomException extends RuntimeException {

    private String message;

    public CustomException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
