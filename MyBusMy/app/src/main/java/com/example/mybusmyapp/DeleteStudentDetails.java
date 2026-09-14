package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class DeleteStudentDetails extends AppCompatActivity {

    private ListView deleteStudentDetails;
    private TextView emptyText;

    ArrayAdapter<String[]> adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student_details);
        deleteStudentDetails=findViewById(R.id.listDeleteStudents);

        String response = Network.connect("http://" + Network.IP + "/bustrackfetch.php",new HashMap<String, String>());

        if (response.equals("0")){
            emptyText.setText("No Record Available");
        }else if (!response.equals("0")) {
            List<String> students = Arrays.asList(response.trim().split("#"));

            List<String[]> studDataList = new ArrayList<>();

            // Convert each bus data string to a String ar ray
            for (String busString : students) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                studDataList.add(busData);
            }

            adapter = new ArrayAdapter<String[]>(this, R.layout.layout_delete_student_details, R.id.textDeleteStudentID, studDataList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewStudentId = view.findViewById(R.id.textDeleteStudentID);
                    TextView textViewStudentName = view.findViewById(R.id.textDeleteStudentName);
                    TextView textViewStudentMobile = view.findViewById(R.id.textDeleteStudentMobile);
                    TextView textViewStudentEmail = view.findViewById(R.id.textDeleteStudentEmail);
                    TextView textViewStudentPass = view.findViewById(R.id.textDeleteStudentPass);
                    TextView textViewStudentAdress = view.findViewById(R.id.textDeleteStudentAddress);

                    Button deleteDetailBtn=view.findViewById(R.id.btnDeleteStudentDetails);
                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewStudentId.setText(busData[1]);
                    textViewStudentName.setText(busData[0]);
                    textViewStudentMobile.setText(busData[3]);
                    textViewStudentEmail.setText(busData[4]);
                    textViewStudentPass.setText(busData[5]);
                    textViewStudentAdress.setText(busData[2]);


                    deleteDetailBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int companyName = getIntent().getIntExtra("studentid", 0);
                            System.out.println("---" + companyName);
                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("studentid", textViewStudentId.getText().toString());
                            String rs = Network.connect("http://" + Network.IP + "/deletebustrack.php", param);
                            studDataList.remove(position);
                            notice();
                        }
                    });

                    return view;
                }
            };
        }
        deleteStudentDetails.setAdapter(adapter);
    }
    private void notice() {
        adapter.notifyDataSetChanged();
    }
}