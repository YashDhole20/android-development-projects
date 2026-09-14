package com.example.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

public class LocationHelper {

    private static final long MIN_TIME_BW_UPDATES = 1000 * 10; // 10 seconds
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10f; // 10 meters
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Context context;

    public interface LocationUpdateListener {
        void onLocationChanged(Location location);
    }

    public LocationHelper(Context context, LocationUpdateListener listener) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                listener.onLocationChanged(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };
    }

    public void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
    }

    public void stopLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }
}
