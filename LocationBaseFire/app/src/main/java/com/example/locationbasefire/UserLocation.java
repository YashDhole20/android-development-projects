package com.example.locationbasefire;

public class UserLocation {

    private Double longitude;
    private Double latitude;

    public UserLocation() {
    }

    public UserLocation(Double latitude, Double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
