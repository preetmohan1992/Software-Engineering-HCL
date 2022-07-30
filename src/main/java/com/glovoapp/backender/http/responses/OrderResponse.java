package com.glovoapp.backender.http.responses;

import com.glovoapp.backender.models.Order;

import java.util.Date;

public class OrderResponse {
    private int id;
    private String description;
    private boolean food;
    private boolean vip;
    private LocationResponse pickup;
    private LocationResponse delivery;
    private Date createdAt;

    private OrderResponse(
            int id,
            String description,
            boolean food,
            boolean vip,
            LocationResponse pickup,
            LocationResponse delivery,
            Date createdAt
    ) {
        this.id = id;
        this.description = description;
        this.food = food;
        this.vip = vip;
        this.pickup = pickup;
        this.delivery = delivery;
        this.createdAt = createdAt;
    }

    public static OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getDescription(),
                order.getFood(),
                order.getVip(),
                LocationResponse.fromLocation(order.getPickup()),
                LocationResponse.fromLocation(order.getDelivery()),
                order.getCreatedAt()
        );
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFood() {
        return food;
    }

    public boolean isVip() {
        return vip;
    }

    public LocationResponse getPickup() {
        return pickup;
    }

    public LocationResponse getDelivery() {
        return delivery;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
