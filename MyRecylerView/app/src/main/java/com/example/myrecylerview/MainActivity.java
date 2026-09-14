package com.example.myrecylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView courseRV = findViewById(R.id.idRVCourse);


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String response = Network.connect("http://" + Network.IP + "/viewtask.php",
                new HashMap<String, String>());

        List<String> students = Arrays.asList(response.split("#"));

        ArrayList<Model> modelArrayList = new ArrayList<>();
        for (String busString : students) {
            String[] busData = busString.split("<br>");
            if (busData.length >= 3) { // Check if busData has at least 3 elements
                String title = busData[0];
                String description = busData[1];
                String location = busData[2];
                modelArrayList.add(new Model(title, description, location));
            }
        }

        Adapterrr courseAdapter = new Adapterrr(this, modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);

    }
}