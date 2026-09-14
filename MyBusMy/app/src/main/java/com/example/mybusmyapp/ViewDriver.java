package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewDriver extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_driver);
        listView = findViewById(R.id.driverLocationList);

        String response = Network.connect("http://" + Network.IP + "/showAllDriver.php", new HashMap<String, String>());

        if (response.equals("0")) {
//            textView.setText("No Record Available");
            Toast.makeText(this, "No Record Available", Toast.LENGTH_SHORT).show();
        } else if (!response.equals("0")) {
            List<String> students = Arrays.asList(response.trim().split("#"));

            List<String[]> studentDataList = new ArrayList<>();


            for (String busString : students) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                studentDataList.add(busData);
            }
//            Toast.makeText(this, "Data" + studentDataList, Toast.LENGTH_SHORT).show();
            ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_bus_reported, R.id.textPresentViewStudentID, studentDataList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewStudentId = view.findViewById(R.id.textPresentViewStudentID);
                    TextView textViewStudentname = view.findViewById(R.id.textPresentViewStudentName);
                    TextView textViewStudentMobile = view.findViewById(R.id.textPresentViewStudentMobile);
                    TextView textViewDate = view.findViewById(R.id.textCurrentData);
                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewStudentId.setText(busData[0]);
                    textViewStudentname.setText(busData[1]);
                    textViewStudentMobile.setText(busData[2]);
                    textViewDate.setText(busData[3]);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(ViewDriver.this, DriverLocationView.class);
                            intent.putExtra("busnumber",textViewDate.getText().toString());
                            startActivity(intent);
                        }
                    });
                    return view;
                }
            };

            listView.setAdapter(adapter);
        }
    }
}