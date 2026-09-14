package com.example.attendanceusinggeofencing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AdminLoginActivity extends AppCompatActivity {

    EditText editusername,editpass;
    Button btnadminlogin;
    TextInputLayout inputusername, inputpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        inputusername = (TextInputLayout) findViewById(R.id.inputusername);
        inputpass = (TextInputLayout) findViewById(R.id.inputpass);
        editusername = (EditText) findViewById(R.id.editusername);
        editpass = (EditText) findViewById(R.id.editpass);
        btnadminlogin = (Button) findViewById(R.id.btnadminlogin);

        btnadminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnadminlogin:
                        if (editusername.getText().toString().trim().length() > 0 && editpass.getText().toString().trim().length() > 0) {
                            if (editusername.getText().toString().trim().equals("admin") && editpass.getText().toString().trim().equals("admin123")) {
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Access Denied", Toast.LENGTH_LONG).show();
                            }
                        }

            }
            }
        });

    }
}
