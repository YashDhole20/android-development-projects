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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewStudentDetails extends AppCompatActivity {

    ListView studentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_details);

        studentDetails = findViewById(R.id.listViewStudents);

        String response = Network.connect("http://" + Network.IP + "/bustrackfetch.php",
                new HashMap<String, String>());

        if (response.equals("0")){
//            textView.setText("No Record Available");
            Toast.makeText(this, "No Record Available", Toast.LENGTH_SHORT).show();
        }
        else if (!response.equals("0")) {
            List<String> students = Arrays.asList(response.trim().split("#"));

            List<String[]> studentDataList = new ArrayList<>();


            for (String busString : students) {
                String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                studentDataList.add(busData);
            }

            ArrayAdapter<String[]> adapter=new ArrayAdapter<String[]>(this,R.layout.layout_view_student_details,R.id.textViewStudentID,studentDataList ){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    String[] busData = getItem(position);

                    TextView textViewStudentId = view.findViewById(R.id.textViewStudentID);
                    TextView textViewStudentname=view.findViewById(R.id.textViewStudentName);
                    TextView textViewStudentMobile = view.findViewById(R.id.textViewStudentMobile);
                    TextView textViewStudentEmail = view.findViewById(R.id.textViewStudentEmail);
                    TextView textViewStudentPass= view.findViewById(R.id.textViewStudentPass);
                    TextView textViewStudentAdress=view.findViewById(R.id.textViewStudentAddress);
                    Button updateStudentBtn=view.findViewById(R.id.btnUpdateStudentDetails);
                    // Assuming each element of busData array corresponds to TextViews in order
                    textViewStudentId.setText(busData[1]);
                    textViewStudentname.setText(busData[0]);
                    textViewStudentMobile.setText(busData[3]);
                    textViewStudentEmail.setText(busData[4]);
                    textViewStudentPass.setText(busData[5]);
                    textViewStudentAdress.setText(busData[2]);

                    updateStudentBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent=new Intent(getApplicationContext(), UpdateStudentDetailTwo.class);
                            intent.putExtra("studentid",textViewStudentId.getText().toString());
                            intent.putExtra("name",textViewStudentname.getText().toString());
                            intent.putExtra("address",textViewStudentAdress.getText().toString());
                            intent.putExtra("mobileno",textViewStudentMobile.getText().toString());
                            intent.putExtra("email",textViewStudentEmail.getText().toString());
                            intent.putExtra("password",textViewStudentPass.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    });
                    return view;
                }
            };

            studentDetails.setAdapter(adapter);
        }
    }
}

