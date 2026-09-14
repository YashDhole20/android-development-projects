package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class ViewAttendanceForStudent extends AppCompatActivity {


    private ListView listView;
    ArrayAdapter<String[]> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendacne_for_student);


        listView=findViewById(R.id.listViewForStudentAttendance);
        // Make network request to retrieve student data
        HashMap<String, String> param = new HashMap<String, String>();
        String    id=getIntent().getStringExtra("studentid");
        param.put("studentid", id);
        String response = Network.connect("http://" + Network.IP + "/showattenndanceforstudent.php", param);

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
            ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(this, R.layout.layout_for_student_attendance, R.id.textPresentViewStudentID, studentDataList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewStudentId = view.findViewById(R.id.textPresentViewStudentID);

                    TextView textViewDate = view.findViewById(R.id.textCurrentData);
                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewStudentId.setText(busData[0]);
                    textViewDate.setText(busData[1]);
                    return view;
                }
            };

            listView.setAdapter(adapter);
//        int i=listView.getAdapter().getCount();
//            getcount.setText(i);

        }
    }
}