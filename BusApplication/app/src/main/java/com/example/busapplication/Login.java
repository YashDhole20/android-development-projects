package com.example.busapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button btnstudentlogin;
    TextView textsignup,textforgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText) findViewById(R.id.editemail);
        password=(EditText) findViewById(R.id.editpassword);
        textforgot=(TextView) findViewById(R.id.textforgot);
        textsignup=(TextView) findViewById(R.id.textsignup);
        btnstudentlogin=(Button) findViewById(R.id.btnstudentlogin);

        btnstudentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                        if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                        }else {

                            HashMap<String, String> param = new HashMap<String, String>();
                            param.put("email", username.getText().toString());
                            param.put("password", password.getText().toString());

                            StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(sb);
                            String id = Network.connect("http://" + Network.IP + "/login.php", param);
                            id = id.trim();

                            id=id;
                            if (id.equals("0")) {

                                Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                                finish();
                            }
            }
        }
        });

    }
    }

