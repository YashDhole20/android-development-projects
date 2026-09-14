package com.example.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class showroom_section extends AppCompatActivity {

    private ImageView logoImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom_section);

        logoImageView = findViewById(R.id.logoImageView);

        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new page here
                openNewPage();
            }
        });
    }

    private void openNewPage() {
        Intent intent = new Intent(showroom_section.this, showroom_login.class);
        startActivity(intent);
    }
}
