package com.example.smarttrafficgame;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class trafficrules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafficrules);

        // Initialize your image views
        ImageView humpImageView = findViewById(R.id.hump);
        ImageView noRightTurnImageView = findViewById(R.id.Norightturn);
        // Add more image views for other elements

        // Set click listeners for each image view
        humpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, hump.class);
                startActivity(intent);
            }
        });

        noRightTurnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, rightturn.class);
                startActivity(intent);
            }
        });

        // Add more click listeners for other elements
        // Initialize your image views
        ImageView stepacentimageview= findViewById(R.id.stepacent);
        ImageView stepdecentImageView = findViewById(R.id.stepdecent);
        // Add more image views for other elements

        // Set click listeners for each image view
        stepacentimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, stepacent.class);
                startActivity(intent);
            }
        });

        stepdecentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, stepacent.class);
                startActivity(intent);
            }
        });
        // Add more click listeners for other elements
        // Initialize your image views
        ImageView leftcurveimageview= findViewById(R.id.leftcurve);
        ImageView manatworkImageView = findViewById(R.id.manatwork);
        // Add more image views for other elements

        // Set click listeners for each image view
        leftcurveimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, noleftturn.class);
                startActivity(intent);
            }
        });

        stepdecentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, stepacent.class);
                startActivity(intent);
            }
        });
        // Add more click listeners for other elements
        // Initialize your image views
        ImageView dangeourusdipimageview= findViewById(R.id.dangepoursdip);
        ImageView stopImageView = findViewById(R.id.stop);
        // Add more image views for other elements

        // Set click listeners for each image view
        dangeourusdipimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, dangerousdip.class);
                startActivity(intent);
            }
        });

        stopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, stop.class);
                startActivity(intent);
            }
        });
        // Add more click listeners for other elements
        // Initialize your image views
        ImageView schoolaheadview= findViewById(R.id.school);
        ImageView noparkingView = findViewById(R.id.noparking);
        // Add more image views for other elements

        // Set click listeners for each image view
        schoolaheadview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, schoolahead.class);
                startActivity(intent);
            }
        });

        noparkingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, noparking.class);
                startActivity(intent);
            }
        });

        // Add more click listeners for other elements
        // Initialize your image views
        ImageView noovertakeiamgeview= findViewById(R.id.noovertake);
        ImageView noentryiamgeView = findViewById(R.id.noentry);
        // Add more image views for other elements

        // Set click listeners for each image view
        noovertakeiamgeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, noovertake.class);
                startActivity(intent);
            }
        });

        noentryiamgeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for No Right Turn image
                Intent intent = new Intent(trafficrules.this, noentry.class);
                startActivity(intent);
            }
        });
        // Add more click listeners for other elements
        // Initialize your image views
        ImageView manatworkiamgeview= findViewById(R.id.manatwork);
        // Set click listeners for each image view
        manatworkiamgeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click for Hump image
                Intent intent = new Intent(trafficrules.this, manatwork.class);
                startActivity(intent);
            }
        });
    }
}
