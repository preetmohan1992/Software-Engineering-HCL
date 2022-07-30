package com.glovoapp.backender.http.responses;

import com.glovoapp.backender.models.Location;

public class LocationResponse {
    private double latitude;
    private double longitude;

    private LocationResponse(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LocationResponse fromLocation(Location location) {
        return new LocationResponse(location.getLatitude(), location.getLongitude());
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
