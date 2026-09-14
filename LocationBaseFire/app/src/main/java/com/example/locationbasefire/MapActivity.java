package com.example.locationbasefire;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private FirebaseAuth auth;
    private DatabaseReference reference, refLocation;
    private Double lat, lon;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
        refLocation = FirebaseDatabase.getInstance().getReference().child("Location");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkLocationPermission();
        fetchUserLocationFromFirebase();
        webView.loadUrl("file:///android_asset/leaflet_map.html");
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    // Inside MapActivity class

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // 10 seconds
        locationRequest.setFastestInterval(5000); // 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    injectUserLocationIntoWebView(lat, lon);
                    String id = getIntent().getStringExtra("email");
                    Double lat = location.getLatitude();
                    Double lon = location.getLongitude();
                    String key = id.replace(".", ",");
                    UserLocation userLocation = new UserLocation(lat, lon);
                    refLocation.child(key).setValue(userLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(MainActivity.this, "" +  lon + " " + lat, Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(MapActivity.this, "Location not Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }


    private void fetchUserLocationFromFirebase() {
        String key = getIntent().getStringExtra("key");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Location").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Double lat = dataSnapshot.child("latitude").getValue(Double.class);
                    Double lon = dataSnapshot.child("longitude").getValue(Double.class);
                    if (lat != null && lon != null) {
                        injectUserLocationIntoWebView(lat, lon);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MapActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void injectUserLocationIntoWebView(Double lat, Double lon) {
        webView.evaluateJavascript("setUserLocation(" + lat + "," + lon + ");", null);
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

    public class WebAppInterface {

        @JavascriptInterface
        public void getUserLocation() {
            if (lat != null && lon != null) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        injectUserLocationIntoWebView(lat, lon);
                    }
                });
            }
        }
    }
}
