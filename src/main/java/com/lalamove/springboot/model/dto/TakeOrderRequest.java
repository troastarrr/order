package com.lalamove.springboot.model.dto;

import javax.validation.constraints.NotNull;

public class TakeOrderRequest {

    @NotNull
    public String status;

    public long id;

    @Override
    public String toString() {
        return "TakeOrderRequest{" +
                "status='" + status + '\'' +
                '}';
    }
}
