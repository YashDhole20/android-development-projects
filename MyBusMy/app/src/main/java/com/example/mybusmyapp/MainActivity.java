package com.example.mybusmyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private TextInputLayout emailWrapper,passwordWrapper;
    private Button loginBtn;
    private TextInputEditText emailInput,passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailWrapper=findViewById(R.id.text_input_layout_email);
        passwordWrapper=findViewById(R.id.text_input_layout_password);
        loginBtn=findViewById(R.id.btndriverLogin);
        emailInput=findViewById(R.id.editDriverLoginUsernm);
        passwordInput=findViewById(R.id.editDriverLoginPassword);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (emailInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("drivername", emailInput.getText().toString());
                    param.put("password", passwordInput.getText().toString());
                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);

                    String res = Network.connect("http://" + Network.IP + "/driverlogin.php", param);

                    List<String> studDataList = Arrays.asList(res.trim().split("<br>"));


                    if (studDataList.get(0).equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessfull", Toast.LENGTH_LONG).show();
                    } else if (!res.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), DriverProfile.class);
                        intent.putExtra("drivername", studDataList.get(0));
                        intent.putExtra("busnumber", studDataList.get(1));
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        }
    }
