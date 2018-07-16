package com.lalamove.springboot.service;

import com.google.maps.errors.NotFoundException;
import com.lalamove.springboot.exceptions.custom.OrderAlreadyBeenTakenException;
import com.lalamove.springboot.model.OrderStatus;
import com.lalamove.springboot.model.dto.*;
import com.lalamove.springboot.model.entity.Order;
import com.lalamove.springboot.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    public static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GoogleDirectionClientService googleDirectionClient;

    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) throws Exception {
        String distance = googleDirectionClient.getDistance(placeOrderRequest);
        if (!StringUtils.isEmpty(distance)) {
            Order order = new Order();
            order.setStatus(OrderStatus.UNASSIGN.toString());
            order.setDistance(distance);
            Order retrieveOrder =orderRepository.save(order);
            logger.info("Saving to database...");
            PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
            placeOrderResponse.setDistance(order.getDistance());
            placeOrderResponse.setId(retrieveOrder.getId());
            placeOrderResponse.setStatus(order.getStatus());
            return placeOrderResponse;
        }
        return null;
    }

    public TakeOrderResponse takeOrder(TakeOrderRequest takeOrderRequest) throws Exception{
        Optional<Order> orderOptional;
        try {
            orderOptional = orderRepository.findById(takeOrderRequest.id);
        }catch (Exception e) {
            throw new NotFoundException("Order not found :" + takeOrderRequest.id);
        }
        Order order = orderOptional.get();
        if (OrderStatus.ORDER_ALREADY_BEEN_TAKEN.toString().equalsIgnoreCase(order.getStatus())) {
            throw new OrderAlreadyBeenTakenException("ORDER_ALREADY_BEEN_TAKEN");
        }
        order.setStatus(OrderStatus.ORDER_ALREADY_BEEN_TAKEN.toString());
        orderRepository.save(order);
        TakeOrderResponse takeOrderResponse = new TakeOrderResponse();
        takeOrderResponse.status = OrderStatus.SUCCESS.toString();
        return takeOrderResponse;
    }

    public List<PlaceOrderResponse> findOrder(OrderListRequest orderListRequest) {
        Sort.Direction direction = getDirection(orderListRequest);
        Pageable pageable = PageRequest.of(orderListRequest.page,orderListRequest.size, direction,"dateCreated");
        List<PlaceOrderResponse> orderResponseList = convertToDto(orderRepository.findAll(pageable));
        return orderResponseList;
    }



    public List<PlaceOrderResponse> convertToDto(Page<Order> orders) {
        List<PlaceOrderResponse> orderResponseList = new ArrayList<>();
        PlaceOrderResponse placeOrderResponse;
        if (orders != null) {
            for (Order order : orders) {
                placeOrderResponse = new PlaceOrderResponse();
                placeOrderResponse.setStatus(order.getStatus());
                placeOrderResponse.setId(order.getId());
                placeOrderResponse.setDistance(order.getDistance());
                orderResponseList.add(placeOrderResponse);
            }
        }

        return orderResponseList;
    }

    public Sort.Direction getDirection(OrderListRequest orderListRequest) {
        return orderListRequest.sort.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }


}
