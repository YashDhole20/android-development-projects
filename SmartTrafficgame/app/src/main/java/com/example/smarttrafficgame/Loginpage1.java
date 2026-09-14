package com.example.smarttrafficgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Loginpage1 extends AppCompatActivity {


    private EditText user,pass;
    private Button loginbtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage1);


        user=findViewById(R.id.etUsername);
        pass=findViewById(R.id.etPassword);
        loginbtn=findViewById(R.id.btnLogin);
        textView=findViewById(R.id.newuser);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("username",user.getText().toString().trim());
                param.put("Password",pass.getText().toString().trim());
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String rs=Network.connect("http://"+Network.IP+"/login.php",param);


                List<String> buses = Arrays.asList(rs.trim().split("#"));

                List<String[]> busDataList = new ArrayList<>();
                for (String busString : buses) {
                    String[] busData = busString.split("<br>"); // Assuming each bus data string is comma-separated
                    busDataList.add(busData);
    }
                String id= Arrays.toString(busDataList.get(0)).trim();
                id = id.replaceAll("[^0-9]", "");
                System.out.println(busDataList.get(0)+"55");
                System.out.println(id+"123");
                if (id.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
                }
                else if (!id.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });

    }
}