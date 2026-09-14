package com.example.ecochargehub;

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

public class admin_evs extends AppCompatActivity {
    private ListView recyclerView;
    Button btnLogout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_evs);

        recyclerView = (ListView) findViewById(R.id.admin_evslist);
       btnLogout =  findViewById(R.id.logout);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HashMap<String, String> param = new HashMap<String, String>();

        String rs = Network.connect("http://" + Network.IP + "/evslist.php",
                param);

        List<String> students = Arrays.asList(rs.trim().split("#"));

        List<String[]> studentDataList = new ArrayList<>();

        for (String busString : students) {
            String[] busData = busString.split("<br>");
            studentDataList.add(busData);
        }

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.admin_evslist, R.id.displayname, studentDataList) {
            @NonNull
            @Override
            public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] busData = getItem(position);

                TextView textViewStudentId = view.findViewById(R.id.displayid);
                TextView textViewStudentname = view.findViewById(R.id.displayname);
                TextView textViewStudentemail = view.findViewById(R.id.displayemail);
                TextView textViewStudentMobile = view.findViewById(R.id.displaymobile);
                TextView textViewStudentcity = view.findViewById(R.id.displaycity);
                Button approve = view.findViewById(R.id.approve);
                Button disapprove = view.findViewById(R.id.disapprove);


                textViewStudentId.setText(busData[0]);
                textViewStudentname.setText(busData[1]);
                textViewStudentMobile.setText(busData[2]);
                textViewStudentemail.setText(busData[3]);
                textViewStudentcity.setText(busData[4]);


                approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String studentId = busData[0];
                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", studentId); // Assuming student_id is the primary key
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
                        String studentId = busData[0];
                        // Assuming 'status' is the column name in the agriculture_registration table
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id", studentId); // Assuming student_id is the primary key
                        param.put("status", "disapprove");

                        String rs = Network.connect("http://" + Network.IP + "/approval.php", param);

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
                        Intent intent = new Intent(admin_evs.this, admin_section.class);
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