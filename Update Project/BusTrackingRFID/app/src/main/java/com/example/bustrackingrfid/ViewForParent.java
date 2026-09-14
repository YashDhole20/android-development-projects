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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewForParent extends AppCompatActivity {
    ListView studDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_for_parent);

        studDetails=findViewById(R.id.idViewModelPresent1);


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String rfid=getIntent().getStringExtra("rfid").trim();
        HashMap<String,String> p=new HashMap<String, String>();
        p.put("rfid",rfid);

        String rs=Network.connect("http://"+Network.IP+"/viewforparent.php",p);

        List<String> stud= Arrays.asList(rs.trim().split("#"));
        List<String[]> studData=new ArrayList<>();
        if (stud.get(0).equals("0")){

        }else if (!rs.equals("0")){
            for (String busString : stud) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                studData.add(busData);
            }



            // Create an ArrayAdapter to populate the ListView with company names
            ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_present_student, R.id.studNamePresentTextView, studData) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewBusNumber = view.findViewById(R.id.studNamePresentTextView);
                    TextView textViewDriverName = view.findViewById(R.id.rfidPresentTextView);
                    TextView textViewphone=view.findViewById(R.id.phonePresentTextView);
                    TextView textViewdriver=view.findViewById(R.id.driverTextView);
                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewBusNumber.setText(busData[0]);
                    textViewDriverName.setText(busData[1]);
                    textViewphone.setText(busData[2]);
                    textViewdriver.setText(busData[3]);

                    String lng = busData[3];
                    String lat=busData[2];
                    String[] parts = lng.split("-");
                    String[] parts1 = lat.split("-");


                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strUri = "http://maps.google.com/maps?q=loc:" +parts1[1].trim() + "," +parts[1].trim()+ " (" + "Bus Location" + ")";
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                            startActivity(intent);
                        }
                    });

                    return view;
                }
            };

            studDetails.setAdapter(adapter);
        }
    }



}

