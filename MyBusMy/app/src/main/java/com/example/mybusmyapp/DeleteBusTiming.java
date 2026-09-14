package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DeleteBusTiming extends AppCompatActivity {

    private ListView deletebusDetails;
    private TextView emptyText;
    private Button deleteDetailBtn;
    ArrayAdapter<String[]> adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bus_timing);

        deletebusDetails=findViewById(R.id.listDeleteBuses);
        emptyText=findViewById(R.id.textEmptyText);


        String response = Network.connect("http://" + Network.IP + "/busdetailsfetch.php",
                new HashMap<String, String>());

        if (response.equals("0")){
            emptyText.setText("No Record Available");
        }

        List<String> buses = Arrays.asList(response.trim().split("#"));

        List<String[]> busDataList = new ArrayList<>();

// Convert each bus data string to a String array
        for (String busString : buses) {
            String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
            busDataList.add(busData);
        }


        // Create an ArrayAdapter to populate the ListView with company names



        adapter = new ArrayAdapter<String[]>(this, R.layout.layout_bus_details_delete,R.id.textDeleteBusNumber , busDataList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] busData = getItem(position);

                TextView textViewBusNumber = view.findViewById(R.id.textDeleteBusNumber);
                TextView textViewDriverName = view.findViewById(R.id.textDeleteDriverName);
                TextView textViewRouteStart = view.findViewById(R.id.textDeleteRouteStart);
                TextView textViewRouteEnd = view.findViewById(R.id.textDeleteRouteEnd);
                TextView textViewTimeStart = view.findViewById(R.id.textDeleteTimeStart);
                TextView textViewTimeEnd = view.findViewById(R.id.textDeleteTimeEnd);

                Button deletebtn=view.findViewById(R.id.btnDeleteBusDetails);

                // Assuming each element of busData array corresponds to TextViews in order
                textViewBusNumber.setText(busData[0]);
                textViewDriverName.setText(busData[1]);
                textViewRouteStart.setText(busData[2]);
                textViewRouteEnd.setText(busData[3]);
                textViewTimeStart.setText(busData[4]);
                textViewTimeEnd.setText(busData[5]);




                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       int companyName = getIntent().getIntExtra("busnumber",0);
                        System.out.println("---"+companyName);
                        HashMap<String,String> param=new HashMap<String, String>();
                        param.put("busnumber", textViewBusNumber.getText().toString());
                        String rs=Network.connect("http://"+Network.IP+"/deletebusdetails.php", param);
                        busDataList.remove(position);
                        notice();
                    }


                });
                return view;
            }


        };

        deletebusDetails.setAdapter(adapter);
    }
    private void notice() {
        adapter.notifyDataSetChanged();
    }
}