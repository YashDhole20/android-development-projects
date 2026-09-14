package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ApproveOfficer extends AppCompatActivity {
    private ListView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_officer);

        recyclerView = (ListView) findViewById(R.id.list_item4);
        Button  btnLogout = findViewById(R.id.logout123);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HashMap<String, String> param = new HashMap<String, String>();

        String rs = Network.connect("http://" + Network.IP + "/display4.php",
                param);

        List<String> students = Arrays.asList(rs.trim().split("#"));

        List<String[]> studentDataList = new ArrayList<>();

        for (String busString : students) {
            String[] busData = busString.split("<br>");
            studentDataList.add(busData);
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApproveOfficer.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_list4, R.id.displayname, studentDataList) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] busData = getItem(position);

                TextView textViewStudentId = view.findViewById(R.id.displayname);
                TextView textViewStudentname = view.findViewById(R.id.displayaddress);
                TextView textViewStudentMobile = view.findViewById(R.id.displaymobile);
                TextView textViewStudentemail = view.findViewById(R.id.displayemail);
                Button approve = view.findViewById(R.id.approve);
                Button disapprove = view.findViewById(R.id.disapprove);

                textViewStudentId.setText(busData[1]);
                textViewStudentname.setText(busData[2]);
                textViewStudentMobile.setText(busData[3]);
                textViewStudentemail.setText(busData[4]);


                approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stu[] = busData[0].split(":");
                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", stu[1]); // Assuming student_id is the primary key
                        param.put("status", "approve");

                        String rs = Network.connect("http://" + Network.IP + "/approval.php", param);

                        // Handle response here
                        System.out.println(rs);

                        // Remove the item from the list and update the ListView
                        studentDataList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                disapprove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stu[] = busData[0].split(":");

                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", stu[1]); // Assuming student_id is the primary key
                        param.put("status", "disapprove");

                        String rs = Network.connect("http://" + Network.IP + "/approval.php", param);

                        // Handle response here
                        System.out.println(rs);

                        // Remove the item from the list and update the ListView
                        studentDataList.remove(position);
                        notifyDataSetChanged();
                    }
                });



                return view;
            }
        };
        recyclerView.setAdapter(adapter);
    }
}