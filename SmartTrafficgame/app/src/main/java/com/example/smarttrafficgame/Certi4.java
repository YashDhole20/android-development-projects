package com.example.smarttrafficgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Certi4 extends AppCompatActivity {

    TextView name,date,grade;
    ImageView cert,sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certi4);

        name=findViewById(R.id.nameTextView3);
        date=findViewById(R.id.dateTextView3);
        grade=findViewById(R.id.gradeTextView3);
        cert=findViewById(R.id.certificateImage3);
        sign=findViewById(R.id.signTextView3);
        String id=getIntent().getStringExtra("id");
        Button btn=findViewById(R.id.next3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YTvideo4.class);
                intent.putExtra("id", id);
                System.out.println(id);
                startActivity(intent);
            }
        });
        HashMap<String,String> p=new HashMap<String ,String>();
        p.put("id",id);
        String rs=Network.connect("http://"+Network.IP+"/certifacte.php",p);
        List<String> dataEntries = Arrays.asList(rs.trim().split("#"));

        // Create a list to hold data for each entry
        List<String[]> data = new ArrayList<>();

        // Split each entry by '<br>' to separate fields
        for (String entry : dataEntries) {
            String[] entryData = entry.split("<br>");
            data.add(entryData);
        }

        // Display retrieved data
        if (data.size() > 0) {
            String[] firstEntryData = data.get(0); // Assuming the first entry holds the main data
            if (firstEntryData.length >= 2) { // Ensure there are at least three fields: name, date, grade
                name.setText(firstEntryData[0]); // Assuming the name is at index 0
                date.setText(firstEntryData[1]); // Assuming the date is at index 1
                // Assuming the grade is at index 2
            }
        }
    }
}