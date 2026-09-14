package com.example.bustrackingrfid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class ViewStudent extends AppCompatActivity {

    ListView studDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        studDetails=findViewById(R.id.idViewModel);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String rs=Network.connect("http://"+Network.IP+"/studentView.php",new HashMap<String, String>());

        List<String> stud= Arrays.asList(rs.trim().split("#"));
        List<String[]> studData=new ArrayList<>();
        if (stud.get(0).equals("0")){

        }else if (!rs.equals("0")){
            for (String busString : stud) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                studData.add(busData);
            }



            // Create an ArrayAdapter to populate the ListView with company names
            ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_view_student, R.id.studNameTextView, studData) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewBusNumber = view.findViewById(R.id.studNameTextView);
                    TextView textViewDriverName = view.findViewById(R.id.rfidTextView);
                    TextView textViewRouteStart = view.findViewById(R.id.addressTextView);
                    TextView textViewRouteEnd = view.findViewById(R.id.studMobileTextView);
                    TextView textViewTimeStart = view.findViewById(R.id.parentMobileTextView);


                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewBusNumber.setText(busData[0]);
                    textViewDriverName.setText(busData[1]);
                    textViewRouteStart.setText(busData[2]);
                    textViewRouteEnd.setText(busData[3]);
                    textViewTimeStart.setText(busData[4]);


                    return view;
                }
            };

            studDetails.setAdapter(adapter);
        }
    }



}
