package com.lalamove.springboot.exceptions.handler;

import com.google.maps.errors.NotFoundException;
import com.lalamove.springboot.exceptions.custom.InvalidOrderRequestException;
import com.lalamove.springboot.exceptions.custom.OrderAlreadyBeenTakenException;
import com.lalamove.springboot.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = { OrderAlreadyBeenTakenException.class})
    protected ResponseEntity<Object> handleOrderAlreadyBeenTakenException(OrderAlreadyBeenTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = {InvalidOrderRequestException.class})
    protected ResponseEntity<Object> handleInvalidRequestException(InvalidOrderRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }


    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalServerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }

}
