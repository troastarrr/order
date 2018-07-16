package com.lalamove.springboot.exceptions.custom;

public class OrderAlreadyBeenTakenException extends Exception {

    public OrderAlreadyBeenTakenException(String message) {

        super(message);
    }
}
