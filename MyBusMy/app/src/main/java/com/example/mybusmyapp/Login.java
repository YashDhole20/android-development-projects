package com.example.mybusmyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Login extends AppCompatActivity {
    private  EditText username, password;
    private Button btnstudentlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.editemail);
        password=(EditText) findViewById(R.id.editpassword);

        btnstudentlogin=(Button) findViewById(R.id.btnstudentlogin);
        String[]data;


        btnstudentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                }else {

                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("studentid", username.getText().toString());
                            param.put("password", password.getText().toString());
                            String id = Network.connect("http://" + Network.IP + "/studentlogin.php", param);
                    List<String> studDataList = Arrays.asList(id.trim().split("<br>"));
                    if (studDataList.get(0).equals("0")) {
                                Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
                    }
                    else if (!id.equals("0")) {
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), StudentProfile.class);
                                intent.putExtra("studentid", studDataList.get(0));
                                intent.putExtra("name",studDataList.get(1));
                                intent.putExtra("mobileno",studDataList.get(2));
                                startActivity(intent);
                                finish();
                    }
            }
        }
        });

    }
    }

