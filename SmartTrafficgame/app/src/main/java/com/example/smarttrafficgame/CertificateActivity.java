package com.example.smarttrafficgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CertificateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);
        ImageView imageView=findViewById(R.id.image);
        Button btn=findViewById(R.id.next);
        String id=getIntent().getStringExtra("id");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), YTvideows1.class);
                    intent.putExtra("id", id);
                    System.out.println(id);
                    startActivity(intent);
            }
        });
    }
}