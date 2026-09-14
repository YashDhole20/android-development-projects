package com.myandroidproject.ecochargehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.myandroidproject.ecochargehub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bottomRightIcon = findViewById(R.id.bottom_right_icon);

        bottomRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity or perform any desired action
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });
    }
}
