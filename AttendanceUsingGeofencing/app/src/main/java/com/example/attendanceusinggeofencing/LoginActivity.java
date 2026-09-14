package com.example.attendanceusinggeofencing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout inputenrollno, inputpass;
    EditText editenrollno, editpass;
    Button btnlogin;
public static String uuid;
TextView txtregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputenrollno = (TextInputLayout) findViewById(R.id.inputenrollno);
        inputpass = (TextInputLayout) findViewById(R.id.inputpass);
        editenrollno = (EditText) findViewById(R.id.editenrollno);
        editpass = (EditText) findViewById(R.id.editpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txtregister=(TextView)findViewById(R.id.txtregister);

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> param = new HashMap<String, String>();
                param.put("enrollno", editenrollno.getText().toString());
                param.put("password", editpass.getText().toString());

                StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(sb);
                String id = Network.connect("http://" + Network.IP + "/login.php", param);
                id = id.trim();
                uuid=id;

                if (id.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                } else {
                    //  Toast.makeText(getApplicationContext(), "Data Inserted successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AttendanceActivity.class);
                    startActivity(intent);

                }}

        });

    }
}
