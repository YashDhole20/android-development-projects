package com.example.cropdiseasedetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    EditText editadminemail, editadminpass;
    Button btnadmin;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        editadminemail=(EditText)findViewById(R.id.editadminemail);
        editadminpass=(EditText)findViewById(R.id.editadminpass);
        btnadmin=(Button) findViewById(R.id.btnadmin);
        btnLogout = findViewById(R.id.btnLogout);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editadminemail.getText().toString().equals("admin") && editadminpass.getText().toString().equals("admin123")){

                    Toast.makeText(AdminLogin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),ApproveOfficer.class);
                    startActivity(in);
                }else

                    Toast.makeText(AdminLogin.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}