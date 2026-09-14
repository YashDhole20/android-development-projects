package com.myandroidproject.ecochargehub;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class user_login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton,btn_stations,btn_showrooms;
    TextView registerText;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);
        btn_stations = findViewById(R.id.btn_stations);
        btn_showrooms = findViewById(R.id.btn_showrooms);
        back = findViewById(R.id.back);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Not Allowed", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("email", emailEditText.getText().toString());
                    param.put("password", passwordEditText.getText().toString());

                    StrictMode.ThreadPolicy sb = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(sb);
                    String ids = Net.connect("http://" + Net.IP + "/user_login.php", param);
                    String id = ids.trim();
                    if (id.equals("0")) {
                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
                    } else if (id.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), user_section.class);
                        startActivity(intent);
                    }
                }
            }
        });

        registerText.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), user_registration.class);
            startActivity(intent);
        });

        btn_stations.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),station_login.class);
            startActivity(intent);
        });

        btn_showrooms.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),showroom_login.class);
            startActivity(intent);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainScreen.class);
            startActivity(intent);

        });
    }
}



