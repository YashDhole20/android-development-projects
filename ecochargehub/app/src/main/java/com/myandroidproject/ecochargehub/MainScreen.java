package com.myandroidproject.ecochargehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ImageView icon1 = findViewById(R.id.icon1);
        ImageView icon2 = findViewById(R.id.icon2);

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity or perform any desired action
                Intent intent = new Intent(MainScreen.this, Admin.class);
                startActivity(intent);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity or perform any desired action
                Intent intent = new Intent(MainScreen.this, user_login.class);
                startActivity(intent);
            }
        });
    }
}