package com.lalamove.springboot.model.dto;


import java.util.List;

/*{
        "origin": ["START_LATITUDE", "START_LONGTITUDE"],
        "destination": ["END_LATITUDE", "END_LONGTITUDE"]

}*/
public class PlaceOrderRequest {

    public List<Double> origin;

    public List<Double> destination;

    @Override
    public String toString() {
        return "PlaceOrderRequest{" +
                "origin=" + origin +
                ", destination=" + destination +
                '}';
    }
}
