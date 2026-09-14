package com.myandroidproject.ecochargehub;

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

public class admin_cs extends AppCompatActivity {
    private ListView recyclerView;
    Button btnLogout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cs);

        recyclerView = (ListView) findViewById(R.id.admin_cslist);
        btnLogout =  findViewById(R.id.logout);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HashMap<String, String> param = new HashMap<String, String>();

        String rs = Net.connect("http://" + Net.IP + "/cslist.php",
                param);

        List<String> students = Arrays.asList(rs.trim().split("#"));

        List<String[]> studentDataList = new ArrayList<>();

        for (String busString : students) {
            String[] busData = busString.split("<br>");
            studentDataList.add(busData);
        }

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.admin_cslist, R.id.displayname, studentDataList) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] busData = getItem(position);

                TextView textViewStudentId = view.findViewById(R.id.displayid);
                TextView textViewStudentname = view.findViewById(R.id.displayname);
                TextView textViewStudentaddress = view.findViewById(R.id.displayaddress);
                TextView textViewStudentcity = view.findViewById(R.id.displaycity);
                TextView textViewStudenttaluka = view.findViewById(R.id.displaytaluka);
                TextView textViewStudentdistrict= view.findViewById(R.id.displaydistrict);
                TextView textViewStudentopentime= view.findViewById(R.id.displayopentime);
                TextView textViewStudentclosetime= view.findViewById(R.id.displayclosetime);
                TextView textViewStudentMobile = view.findViewById(R.id.displaymobile);
                TextView textViewStudentpower = view.findViewById(R.id.displaypower);
                Button approve = view.findViewById(R.id.approve);
                Button disapprove = view.findViewById(R.id.disapprove);


                textViewStudentId.setText(busData[0]);
                textViewStudentname.setText(busData[1]);
                textViewStudentaddress.setText(busData[2]);
                textViewStudentcity.setText(busData[3]);
                textViewStudenttaluka.setText(busData[4]);
                textViewStudentdistrict.setText(busData[5]);
                textViewStudentopentime.setText(busData[6]);
                textViewStudentclosetime.setText(busData[7]);
                textViewStudentMobile.setText(busData[8]);
                textViewStudentpower.setText(busData[9]);




                approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String studentId = busData[0];
                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", studentId); // Assuming student_id is the primary key
                        param.put("status", "approve");

                        String rs = Net.connect("http://" + Net.IP + "/approval2.php", param);

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
                        String studentId = busData[0];
                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", studentId); // Assuming student_id is the primary key
                        param.put("status", "disapprove");

                        String rs = Net.connect("http://" + Net.IP + "/approval2.php", param);

                        // Handle response here
                        System.out.println(rs);

                        // Remove the item from the list and update the ListView
                        studentDataList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_cs.this, admin_section.class);
                        startActivity(intent);
                        finish();
                    }
                });
                return view;
            }
        };
        recyclerView.setAdapter(adapter);
    }
}