package com.example.bustrackingrfid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PresentStudentForParent extends AppCompatActivity {
    DrawView drawView;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_student_for_parent);

        Button viewChild=findViewById(R.id.btnViewStudent);
        Button about=findViewById(R.id.btnAboutus);
        Button viewLocation=findViewById(R.id.btnlocation);
        Button logout=findViewById(R.id.btnlogout);
        rl=findViewById(R.id.layoutR);
        viewChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PresentStudentForParent.this,ViewForParent.class);
                intent.putExtra("rfid",getIntent().getStringExtra("rfid").trim());
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PresentStudentForParent.this, Dashboard.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PresentStudentForParent.this,  LoginOption.class));
                finish();
            }
        });
        ImageView imageView=findViewById(R.id.imageDriverLogo);
        drawView = new DrawView(PresentStudentForParent.this,imageView,viewChild,18,18,18,18);
        rl.addView(drawView);
        viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("rfid",getIntent().getStringExtra("rfid").trim());
                String response = Network.connect("http://" + Network.IP + "/showLocation.php", params);

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
            }
        });
    }
}