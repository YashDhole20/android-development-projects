package com.myandroidproject.ecochargehub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.myandroidproject.ecochargehub.R;

public class admin_section extends AppCompatActivity {

    ImageView logout,csimage,evsimage;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_section);

        logout = findViewById(R.id.logout);
        csimage = findViewById(R.id.csimage);
        evsimage = findViewById(R.id.evsimage);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new page for logout
                Intent intent = new Intent(admin_section.this, MainScreen.class);
                startActivity(intent);
            }
        });

        csimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new page for vehicles
                Intent intent = new Intent(admin_section.this, admin_cs.class);
                startActivity(intent);
            }
        });

        evsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new page for user
                Intent intent = new Intent(admin_section.this, admin_evs.class);
                startActivity(intent);
            }
        });
    }
}
