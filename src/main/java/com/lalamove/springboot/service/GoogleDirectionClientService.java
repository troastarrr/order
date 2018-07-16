package com.lalamove.springboot.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.lalamove.springboot.application.OrderConfiguration;
import com.lalamove.springboot.model.dto.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GoogleDirectionClientService {

    @Autowired
    private OrderConfiguration orderConfiguration;

    private GeoApiContext context;

    @PostConstruct
    private void init(){
        context = new GeoApiContext().setApiKey(orderConfiguration.getApiKey());
    }

    public String getDistance(PlaceOrderRequest placeOrderRequest) throws Exception {
        String distance = null;
        try {
            DirectionsRoute[] calculatedRoutes = DirectionsApi.newRequest(context)
                    .origin(new LatLng(placeOrderRequest.origin.get(0), placeOrderRequest.origin.get(1)))
                    .destination(new LatLng(placeOrderRequest.destination.get(0),placeOrderRequest.destination.get(1))).await();
            //Get the first value from array
            if (calculatedRoutes != null && calculatedRoutes.length != 0) {
                distance = calculatedRoutes[0].legs[0].distance.toString();
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Unable to find route.");
        } catch (Exception e) {
            throw new Exception("Error calling google map client.");
        }
        return distance;
    }
}
