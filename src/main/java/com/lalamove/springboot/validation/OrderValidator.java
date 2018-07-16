package com.lalamove.springboot.validation;

import com.lalamove.springboot.model.dto.PlaceOrderRequest;
import com.lalamove.springboot.model.dto.TakeOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrderValidator {

    public static final Logger logger = LoggerFactory.getLogger(OrderValidator.class);

    public boolean isValidOrderRequest(PlaceOrderRequest order) {
        if (!isValidList(order.destination) || !isValidList(order.origin)) {
            logger.info("Invalid request order, order must have longtitude and latitude ",order);
            return false;
        }
        return true;
    }
    private boolean isValidList(List<Double> list) {
        if (!CollectionUtils.isEmpty(list)) {
            return isValidRange(list.size());
        }
        return false;
    }


    public boolean isValidTakerOrderRequest(TakeOrderRequest order) {
        if (!StringUtils.isEmpty(order.status) && ("taken".equalsIgnoreCase(order.status))) {
            return true;
        }
        return false;
    }

    private boolean isValidRange(int number) {
        return number == 2;
    }
}
