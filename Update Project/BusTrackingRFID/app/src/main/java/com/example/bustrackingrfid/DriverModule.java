package com.example.bustrackingrfid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;
import java.util.HashMap;

public class DriverModule extends AppCompatActivity {

    TextView _latitude, _longitude;
    ProgressBar _progressBar;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView locationTextView;

    private Button locationBtn;
    private Double lat,lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_module);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationTextView = findViewById(R.id.longitude);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }

        Intent intent = new Intent(getApplicationContext(), AdminPanel.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lo);

//        locationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String strUri = "http://maps.google.com/maps?q=loc:" +lat + "," +lo+ " (" + "Label which you want" + ")";
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
//                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//                startActivity(intent);
//
//            }
//        });
    }

    private void startLocationUpdates () {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000); // 10 seconds
        locationRequest.setFastestInterval(2000); // 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    lat = location.getLatitude();
                    lo = location.getLongitude();
                    Toast.makeText(DriverModule.this, "" + lat + " " + lo, Toast.LENGTH_SHORT).show();
                    locationTextView.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
                    HashMap<String, String> loc = new HashMap<String, String>();
                    String driver_id= getIntent().getStringExtra("id").trim();
                    loc.put("latitude", String.valueOf(location.getLatitude()));
                    loc.put("longitude", String.valueOf(location.getLongitude()));
                    loc.put("driver_id",driver_id);
                    String rs = Network.connect("http://" + Network.IP + "/updatelocation.php", loc);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                // Permission denied.
            }
        }
    }
}
