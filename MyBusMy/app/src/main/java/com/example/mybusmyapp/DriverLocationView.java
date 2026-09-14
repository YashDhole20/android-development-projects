package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DriverLocationView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_location_view);



        String no = getIntent().getStringExtra("busnumber");
        String no1[]=no.split(":");
        HashMap<String, String> params = new HashMap<>();
        params.put("busnumber", no1[1]);
        String response = Network.connect("http://" + Network.IP + "/getDriverLocation.php", params);

        // Split the response by "<br>"
        List<String> locations = Arrays.asList(response.trim().split("<br>"));

        if (locations.size() == 2) {
            String lat = locations.get(0).trim();
            String lng = locations.get(1).trim();

            String strUri = "http://maps.google.com/maps?q=loc:" +lat + "," +lng+ " (" + "Bus Location" + ")";
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
        }
            // Handle invalid response

    }

    @Override
    public void onBackPressed() {
        // Go back to the main activity
        super.onBackPressed();
        Intent intent = new Intent(DriverLocationView.this, ParentModule.class);
        startActivity(intent);
    }

}
