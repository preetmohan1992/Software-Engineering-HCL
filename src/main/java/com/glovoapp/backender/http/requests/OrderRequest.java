package com.glovoapp.backender.http.requests;

import com.glovoapp.backender.models.Order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderRequest {

    @NotBlank(message = "The [description] field is required")
    @Size(max = 50, message = "The [description] field should be less than 50 characters.")
    private String description;

    @NotNull(message = "The [food] field is required")
    private boolean food;

    @NotNull(message = "The [vip] field is required")
    private boolean vip;

    @NotNull(message = "The [pickup] field is required")
    private LocationRequest pickup;

    @NotNull(message = "The [delivery] field is required")
    private LocationRequest delivery;

    public String getDescription() {
        return description;
    }

    public boolean isFood() {
        return food;
    }

    public boolean isVip() {
        return vip;
    }

    public LocationRequest getPickup() {
        return pickup;
    }

    public LocationRequest getDelivery() {
        return delivery;
    }

    public Order toOrder() {
        return new Order(
                description,
                food,
                vip,
                pickup.toLocation(),
                delivery.toLocation()
        );
    }
}
