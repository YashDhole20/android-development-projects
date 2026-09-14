package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ViewBusDetails extends AppCompatActivity {

    private ListView busDetails;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus_details);

        busDetails = findViewById(R.id.listViewBuses);
        textView=findViewById(R.id.textEmptyTextView);

        String response = Network.connect("http://" + Network.IP + "/busdetailsfetch.php",
                new HashMap<String, String>());
        List<String> buses = Arrays.asList(response.trim().split("#"));

        List<String[]> busDataList = new ArrayList<>();
        if (buses.get(0).equals("0")){
            startActivity(new Intent(getApplicationContext(), NoRecord.class));
        }
        else if (!response.equals("0")){


// Convert each bus data string to a String array
            for (String busString : buses) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                busDataList.add(busData);
            }



            // Create an ArrayAdapter to populate the ListView with company names
            ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_bus_detail_view, R.id.textViewBusNumber, busDataList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewBusNumber = view.findViewById(R.id.textViewBusNumber);
                    TextView textViewDriverName = view.findViewById(R.id.textViewDriverName);
                    TextView textViewRouteStart = view.findViewById(R.id.textViewRouteStart);
                    TextView textViewRouteEnd = view.findViewById(R.id.textViewRouteEnd);
                    TextView textViewTimeStart = view.findViewById(R.id.textViewTimeStart);
                    TextView textViewTimeEnd = view.findViewById(R.id.textViewTimeEnd);

                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewBusNumber.setText(busData[0]);
                    textViewDriverName.setText(busData[1]);
                    textViewRouteStart.setText(busData[2]);
                    textViewRouteEnd.setText(busData[3]);
                    textViewTimeStart.setText(busData[4]);
                    textViewTimeEnd.setText(busData[5]);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(ViewBusDetails.this, DriverLocationView.class);
                            intent.putExtra("busnumber",textViewBusNumber.getText().toString());
                            startActivity(intent);
                        }
                    });

                    return view;
                }
            };

            busDetails.setAdapter(adapter);
        }
    }



    }
