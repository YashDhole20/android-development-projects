package com.example.cropdiseasedetection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class showpolicies extends AppCompatActivity {
    private ListView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpolicies);

        recyclerView = (ListView) findViewById(R.id.list_item3);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HashMap<String, String> param = new HashMap<String, String>();

        String rs = Network.connect("http://" + Network.IP + "/display3.php",
                param);

        List<String> students = Arrays.asList(rs.trim().split("#"));

        List<String[]> studentDataList = new ArrayList<>();

        for (String busString : students) {
            String[] busData = busString.split("<br>");
            studentDataList.add(busData);
        }

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_list3, R.id.displaytitle, studentDataList) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] busData = getItem(position);

                TextView textViewStudentId = view.findViewById(R.id.displaytitle);
                TextView textViewStudentname = view.findViewById(R.id.displaydescription);


                textViewStudentId.setText(busData[1]);
                textViewStudentname.setText(busData[2]);

//                String rs = Network.connect("http://"+ Network.IP +"/policies.php", param);

                System.out.println(rs);

                // Notify the adapter about the change
                notifyDataSetChanged();

                return view;
            }
        };
        recyclerView.setAdapter(adapter);
    }
}