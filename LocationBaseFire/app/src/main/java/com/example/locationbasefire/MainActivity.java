package com.example.locationbasefire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button add, view, delete, log;
    private TextView user, mobile;
    private DatabaseReference reference, refLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private FirebaseAuth auth;
    private Double lat, lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btnAddTask);
        view = findViewById(R.id.btnViewTask);
        delete = findViewById(R.id.btnNearby);
        log = findViewById(R.id.btnLogOut);
        user = findViewById(R.id.textUsername);
        mobile = findViewById(R.id.textMobile);
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        refLocation = FirebaseDatabase.getInstance().getReference().child("Location");
        auth = FirebaseAuth.getInstance();


        String email = getIntent().getStringExtra("email");
        String key = email.replace(".", ",");

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                user.setText(user1.getUsername());
                mobile.setText(user1.getMobile());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTask.class);
                intent.putExtra("user", user.getText().toString().trim());
                intent.putExtra("mobile", mobile.getText().toString().trim());
                startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewTask.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("key", key);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                stopLocationUpdates(); // Stop location updates when logging out
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                Toast.makeText(MainActivity.this, "Log Out ", Toast.LENGTH_SHORT).show();
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                startLocationUpdates();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is logged in and start location updates
        if (FirebaseAuth.getInstance().getCurrentUser() != null && locationCallback == null) {
//            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // No need to stop location updates here
    }

    @Override
    protected void onStop() {
        super.onStop();
        // No need to stop location updates here
    }

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
                    lo = location.getLongitude();
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
                            Toast.makeText(MainActivity.this, "Location not Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
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

    private void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
            locationCallback = null; // Reset locationCallback
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates(); // Stop location updates when the activity is destroyed
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
