package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class farmer_login extends AppCompatActivity {

    EditText username, password;
    Button btnfarmerlogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        username = findViewById(R.id.editemail);
        password = findViewById(R.id.editpassword);
        btnfarmerlogin = findViewById(R.id.btnfarmerlogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnfarmerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("email", username.getText().toString());
                    param.put("password", password.getText().toString());

                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);
                    String ids = Network.connect("http://" + Network.IP + "/farmer_login.php", param);
                    String id = ids.trim();
                    if (id.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
                    } else if (id.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), farmersection.class);
                        startActivity(intent);
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), farmer_registration.class); // Replace RegistrationActivity with your actual registration activity class
                startActivity(intent);
            }
        });
    }
}


//package com.example.cropdiseasedetection;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.util.HashMap;
//
//public class farmer_login extends AppCompatActivity {
//
//    EditText username, password;
//    Button btnfarmerlogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farmer_login);
//
//        username=(EditText) findViewById(R.id.editemail);
//        password=(EditText) findViewById(R.id.editpassword);
//        btnfarmerlogin=(Button) findViewById(R.id.btnfarmerlogin);
//
//        btnfarmerlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
//                            Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
//                        }else {
//
//                            HashMap<String, String> param = new HashMap<String, String>();
//                            param.put("email", username.getText().toString());
//                            param.put("password", password.getText().toString());
//
//                            StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                            StrictMode.setThreadPolicy(sb);
//                            String ids = Network.connect("http://"+Network.IP+"/farmer_login.php", param);
//                            String id=ids.trim();
//                            if (id.equals("0")) {
//                                Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
//                            } else if (id.equals("1")){
//                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(getApplicationContext(), farmersection.class);
//                                startActivity(intent);
//                            }
//                        }
//                }
//
//
//        });
//
//    }
//}