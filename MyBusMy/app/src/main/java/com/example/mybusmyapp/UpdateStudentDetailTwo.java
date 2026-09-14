package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UpdateStudentDetailTwo extends AppCompatActivity {


    private EditText studname;
    private  EditText studId;
    private EditText studemail;
    private EditText studpassword;
    private EditText studaddress;
    private EditText studmobile;

    private Button saveUpBtn;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_detail_two);

        studname=findViewById(R.id.editUpdatetStudentName);
        studId=findViewById(R.id.editUpdateStudentId);
        studaddress=findViewById(R.id.editUpdateAddress);
        studemail=findViewById(R.id.editUpdateEmail);
        studmobile=findViewById(R.id.editUpdateMobile);
        studpassword=findViewById(R.id.editUpdatePass);
        saveUpBtn=findViewById(R.id.btnUpdate);

        HashMap<String, String> param = new HashMap<String, String>();

        System.out.println(getIntent() .getStringExtra("studentid"));
         studname.setText(getIntent().getStringExtra("name"));
        studId.setText(getIntent() .getStringExtra("studentid"));
        studaddress.setText(getIntent().getStringExtra("address"));
        studmobile.setText(getIntent().getStringExtra("mobileno"));
        studemail.setText(getIntent().getStringExtra("email"));
        studpassword.setText(getIntent().getStringExtra("password"));


        saveUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = studname.getText().toString().trim();
                final String address = studaddress.getText().toString().trim();
                final String email = studemail.getText().toString().trim();
                final String password = studpassword.getText().toString().trim();
                final String mobile = studmobile.getText().toString().trim();
                HashMap<String, String> params = new HashMap<String, String>();

                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .penaltyFlashScreen()
                        .penaltyDeath()
                        .build());
                params.put("name", name);
                params.put("studentid", studId.getText().toString());
                params.put("address", address);
                params.put("mobileno", mobile);
                params.put("email", email);
                params.put("password", password);

                //returing the response

                String rs = Network.connect("http://" + Network.IP + "/updatestudent.php", params);
                System.out.println("resp" + rs);
                if (rs.trim().equals("0")) {
                    Toast.makeText(getApplicationContext(), "Wrong Info", Toast.LENGTH_LONG).show();

                } else if (rs.trim() != "0") {
                    Toast.makeText(getApplicationContext(), " Updated ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AdminPanel.class));
                    finish();
                }
            }
        });

    }
}