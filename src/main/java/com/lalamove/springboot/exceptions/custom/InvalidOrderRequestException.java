package com.lalamove.springboot.exceptions.custom;

public class InvalidOrderRequestException extends  Exception{

    public InvalidOrderRequestException(String message) {
        super(message);
    }
}
