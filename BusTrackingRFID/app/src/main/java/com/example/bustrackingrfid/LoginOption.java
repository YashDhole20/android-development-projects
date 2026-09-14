package com.example.bustrackingrfid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        Button admin=findViewById(R.id.btnAdmin);
        Button driver=findViewById(R.id.btnDriver);
        Button parent=findViewById(R.id.btnParent);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginOption.this,Login.class));
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginOption.this,DriverLogin.class));
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginOption.this,ParentLogin.class));
            }
        });


    }


}