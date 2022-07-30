package com.glovoapp.backender.http.requests;

import com.glovoapp.backender.models.Location;

import javax.validation.constraints.NotNull;

public class LocationRequest {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    Location toLocation() {
        return new Location(latitude, longitude);
    }
}
