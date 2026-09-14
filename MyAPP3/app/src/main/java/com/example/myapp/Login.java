package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText username, password;
    private Button loginBtn,adminBtn;
    private TextView gotoSignup, forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editTxtLoginUserNm);
        password = (EditText) findViewById(R.id.editTxtLoginPassword);
        loginBtn = (Button) findViewById(R.id.btnLogin);
        gotoSignup = findViewById(R.id.txtGotoSignup);
        forgetPassword = findViewById(R.id.txtForgetPass);
        adminBtn=findViewById(R.id.btnAdmin);
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( getApplicationContext(), AdminLogin.class);
                startActivity(intent);
                Toast.makeText(Login.this, "Goto Admin Login", Toast.LENGTH_SHORT).show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> param = new HashMap<String, String>();

                if (TextUtils.isEmpty(username.getText().toString().trim())) {
                    username.setError("Please enter username");
                    username.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }


//        String [] se=text.getText().toString().split(",");
                param.put("uname",username.getText().toString());
                param.put("pass",password.getText().toString());
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .penaltyDeath()
                .build());
                String rs = Network.connect("http://" + Network.IP + "/login.php",param);
//                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .penaltyFlashScreen()
//                        .penaltyDeath()
//                        .build());;

//                System.out.println("ff"+rs);
////                username.getText().toString().equals("user") && password.getText().toString().equals("123")
                Toast.makeText(Login.this, "abcs"+rs, Toast.LENGTH_SHORT).show();
                if (rs.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Login Not Successfully..", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Successfully..", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(Login.this, MainActivity_optionPage.class);
                    startActivity(in);
                }


//                StringRequest stringRequest = new StringRequest(Request.Method.GET, rs,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                int rep=Integer.parseInt(response);
//                                if(rep!=0){
//                                    Intent intent=new Intent(Login.this,MainActivity_optionPage.class);
//                                    startActivity(intent);
//                                }
//                                else{
//                                    Toast.makeText(Login.this, "Invalid Username and password", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // Handle error response
//                                Log.e("Error", "Error in network request: " + error.getMessage());
//                            }
//                        });
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity_forgetPassword.class);
                startActivity(intent);
            }
        });

    }
}