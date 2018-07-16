package com.lalamove.springboot.model.dto;

/*{
        "id": <order_id>,
        "distance": <total_distance>,
        "status": "UNASSIGN"
}*/
public class PlaceOrderResponse {

    private long id;

    private String distance;

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlaceOrderResponse{" +
                "id=" + id +
                ", distance=" + distance +
                ", status='" + status + '\'' +
                '}';
    }
}
