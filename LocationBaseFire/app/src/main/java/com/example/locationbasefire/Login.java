package com.example.locationbasefire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    private Button login;
    private EditText email,pass;
    private FirebaseAuth auth;
    private TextView gotoReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        email = findViewById(R.id.editTxtLoginUserNm);
        pass = findViewById(R.id.editTxtLoginPassword);
        gotoReg = findViewById(R.id.txtGotoSignup);
        auth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = email.getText().toString().trim();
                String p = pass.getText().toString().trim();

                if (TextUtils.isEmpty(e)) {
                    email.setError("Enter Email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(p)) {
                    pass.setError("Enter Password");
                    pass.requestFocus();
                    return;
                }

                auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            if (auth.getCurrentUser().isEmailVerified()){
                            System.out.println(task.getException());
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("email", email.getText().toString().trim());
                            startActivity(intent);
                            finish();
//                            }
//                            else {
//                                Toast.makeText(Login.this, "Please Verify your email first..", Toast.LENGTH_SHORT).show();
//                            }

                        } else {
                            Toast.makeText(Login.this, "Invalid Details" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        gotoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
    }
}