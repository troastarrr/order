package com.lalamove.springboot.validation;


import com.lalamove.springboot.model.dto.PlaceOrderRequest;
import com.lalamove.springboot.model.dto.TakeOrderRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderValidationTest {

    private OrderValidator classUnderTest = new OrderValidator();

    @Test
    public void givenValidOrderRequestThenReturnTrue() {
        PlaceOrderRequest mockRequest = new PlaceOrderRequest();
        mockRequest.destination = new ArrayList(Arrays.asList(1.30,20));
        mockRequest.origin = new ArrayList(Arrays.asList(2.30,40));
        Assert.assertEquals(true,classUnderTest.isValidOrderRequest(mockRequest));
    }

    @Test
    public void givenInvalidOrderRequestThenReturnFalse() {
        PlaceOrderRequest mockRequest = new PlaceOrderRequest();
        mockRequest.destination = null;
        mockRequest.origin = null;
        Assert.assertEquals(false,classUnderTest.isValidOrderRequest(mockRequest));
    }

    @Test
    public void givenValidTakeOrderRequestThenReturnTrue() {
        TakeOrderRequest takeOrderRequest = new TakeOrderRequest();
        takeOrderRequest.id = 123L;
        takeOrderRequest.status = "taken";
        Assert.assertEquals(true,classUnderTest.isValidTakerOrderRequest(takeOrderRequest));
    }

    @Test
    public void givenInvalidTakeOrderRequestThenReturnFalse() {
        TakeOrderRequest takeOrderRequest = new TakeOrderRequest();
        takeOrderRequest.id = 123L;
        takeOrderRequest.status = "invalid";
        Assert.assertEquals(false,classUnderTest.isValidTakerOrderRequest(takeOrderRequest));
    }


}
