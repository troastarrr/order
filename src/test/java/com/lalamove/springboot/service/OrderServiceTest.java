package com.lalamove.springboot.service;

import com.lalamove.springboot.exceptions.custom.OrderAlreadyBeenTakenException;
import com.lalamove.springboot.model.OrderStatus;
import com.lalamove.springboot.model.dto.*;
import com.lalamove.springboot.model.entity.Order;
import com.lalamove.springboot.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;


    @Mock
    private GoogleDirectionClientService googleDirectionClient;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenValidPlaceOrderRequestThenReturnResponse() throws Exception {
        PlaceOrderRequest mockRequest = new PlaceOrderRequest();
        mockRequest.destination = new ArrayList(Arrays.asList(1.30,20));
        mockRequest.origin = new ArrayList(Arrays.asList(2.30,40));
        Mockito.when(googleDirectionClient.getDistance(mockRequest)).then(e -> "74.m");
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(mockOrder());
        PlaceOrderResponse placeOrderResponse = orderService.placeOrder(mockRequest);
        Assert.assertEquals(OrderStatus.UNASSIGN.toString(),placeOrderResponse.getStatus());
        Assert.assertEquals("74.m",placeOrderResponse.getDistance());
    }

    @Test
    public void givenValidTakeOrderRequestThenReturnResponse() throws Exception {
        TakeOrderRequest takeOrderRequest = new TakeOrderRequest();
        takeOrderRequest.id = 123L;
        takeOrderRequest.status = "taken";
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of( new Order()));
        TakeOrderResponse placeOrderResponse = orderService.takeOrder(takeOrderRequest);
        Assert.assertEquals(OrderStatus.SUCCESS.toString(),placeOrderResponse.status);

    }

    @Test(expected = OrderAlreadyBeenTakenException.class)
    public void givenNonExistentIdThenThrowOrderAlreadyBeenTakenException() throws Exception {
        TakeOrderRequest takeOrderRequest = new TakeOrderRequest();
        takeOrderRequest.id = 12312312312L;
        takeOrderRequest.status = "taken";
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
        TakeOrderResponse placeOrderResponse = orderService.takeOrder(takeOrderRequest);
        Assert.assertEquals(OrderStatus.SUCCESS.toString(),placeOrderResponse.status);

    }

    @Test
    public void givenValidFindOrderrRequestThenReturnResponse() throws Exception {
        OrderListRequest orderListRequest = new OrderListRequest();
        orderListRequest.sort = "asc";
        orderListRequest.size = 10;
        orderListRequest.page = 0;
        Mockito.when(orderService.findOrder(orderListRequest)).then(e -> mockOrderResponse(orderListRequest));

    }

    public List<PlaceOrderResponse> mockOrderResponse(OrderListRequest orderListRequest) {
        List<PlaceOrderResponse> orderResponseList = new ArrayList<>();
        PlaceOrderResponse placeOrderResponse = null;
        for (int x = 0 ; x < orderListRequest.size ; x ++) {
            placeOrderResponse.setDistance("23.9 m");
            placeOrderResponse.setId(123);
            placeOrderResponse.setStatus(OrderStatus.SUCCESS.toString());
            orderResponseList.add(placeOrderResponse);
        }
        return orderResponseList;
    }


    private Order mockOrder() {
        Order order = new Order();
        order.setId(123L);
        order.setStatus(OrderStatus.ORDER_ALREADY_BEEN_TAKEN.toString());
        return order;
    }

}
