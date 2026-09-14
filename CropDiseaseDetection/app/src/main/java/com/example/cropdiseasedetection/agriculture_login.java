package com.example.cropdiseasedetection;

import android.annotation.SuppressLint;
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

public class agriculture_login extends AppCompatActivity {

    EditText agreditemail, agreditpassword;
    Button agrbtnlogin;
    TextView registerText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_login);

        agreditemail = findViewById(R.id.agreditemail);
        agreditpassword = findViewById(R.id.agreditpassword);
        agrbtnlogin = findViewById(R.id.agrbtnlogin);
        registerText = findViewById(R.id.registerText);

        agrbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agreditemail.getText().toString().isEmpty() || agreditpassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("email", agreditemail.getText().toString());
                    param.put("password", agreditpassword.getText().toString());

                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);
                    String response = Network.connect("http://" + Network.IP + "/agriculture_login.php", param);

                    if (response.trim().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
                    } else if (response.trim().equals("*")) {
                        Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
                    } else if (response.trim().equals("&")) {
                        Toast.makeText(getApplicationContext(), "Your approval is discarded. You cannot log in.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), agriculture_section.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        // Set click listener for the registration text
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open registration page when clicked
                Intent intent = new Intent(agriculture_login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
//package com.example.cropdiseasedetection;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.HashMap;
//
//public class agriculture_login extends AppCompatActivity {
//
//    EditText agreditemail, agreditpassword;
//    Button agrbtnlogin;
//    TextView registerText;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_agriculture_login);
//
//        agreditemail = findViewById(R.id.agreditemail);
//        agreditpassword = findViewById(R.id.agreditpassword);
//        agrbtnlogin = findViewById(R.id.agrbtnlogin);
//        registerText = findViewById(R.id.registerText);
//
//        agrbtnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (agreditemail.getText().toString().isEmpty() || agreditpassword.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
//                } else {
//                    HashMap<String, String> param = new HashMap<String, String>();
//                    param.put("email", agreditemail.getText().toString());
//                    param.put("password", agreditpassword.getText().toString());
//
//                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(sb);
//                    String id = Network.connect("http://" + Network.IP + "/agriculture_login.php", param);
//                    id = id.trim();
//
//                    if (id.equals("0")) {
//                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
//                    } else if (id.equals("approval is pending")) {
//                        Toast.makeText(getApplicationContext(), "Approval is pending. You cannot log in yet.", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(), agriculture_section.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            }
//        });
//
//        // Set click listener for the registration text
//        registerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open registration page when clicked
//                Intent intent = new Intent(agriculture_login.this, Register.class);
//                startActivity(intent);
//            }
//        });
//    }
//}



//package com.example.cropdiseasedetection;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.HashMap;
//
//public class agriculture_login extends AppCompatActivity {
//
//    EditText agreditemail, agreditpassword;
//    Button agrbtnlogin;
//    TextView registerText;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_agriculture_login);
//
//        agreditemail = findViewById(R.id.agreditemail);
//        agreditpassword = findViewById(R.id.agreditpassword);
//        agrbtnlogin = findViewById(R.id.agrbtnlogin);
//        registerText = findViewById(R.id.registerText);
//
//        agrbtnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (agreditemail.getText().toString().isEmpty() || agreditpassword.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
//                } else {
//                    HashMap<String, String> param = new HashMap<String, String>();
//                    param.put("email", agreditemail.getText().toString());
//                    param.put("password", agreditpassword.getText().toString());
//
//                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                    StrictMode.setThreadPolicy(sb);
//                    String id = Network.connect("http://" + Network.IP + "/agriculture_login.php", param);
//                    id = id.trim();
//
//                    id = id;
//                    if (id.equals("0")) {
//                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getApplicationContext(), agriculture_section.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            }
//        });
//
//        // Set click listener for the registration text
//        registerText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open registration page when clicked
//                Intent intent = new Intent(agriculture_login.this ,  Register.class);
//                startActivity(intent);
//            }
//        });
//    }
//}