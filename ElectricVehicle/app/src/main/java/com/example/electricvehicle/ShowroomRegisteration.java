package com.example.electricvehicle;

import static android.content.res.ColorStateList.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class ShowroomRegisteration extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroom_registration);

        EditText showroomName=findViewById(R.id.editShowroom);
        EditText usernameShow=findViewById(R.id.editUsername);
        EditText emailShow=findViewById(R.id.editShowEmail);
        EditText mobileShow=findViewById(R.id.editShowMobileNo);
        EditText passwordShow=findViewById(R.id.editShowPassword);
        EditText confShow=findViewById(R.id.editShowConfirmPassword);

        TextView showroomNameText=findViewById(R.id.textShowroom);
        TextView usernameShowText=findViewById(R.id.textUsername);
        TextView emailShowText=findViewById(R.id.textShowEmail);
        TextView mobileShowText=findViewById(R.id.textShowMobileNo);
        TextView passwordShowText=findViewById(R.id.textShowPassword);
        TextView gotoLogin=findViewById(R.id.textShowGotoLogin);
        TextView passwordErrorTextView = findViewById(R.id.passwordErrorTextView);

        TextInputLayout showTextInputLayout=findViewById(R.id.textInputShowroom);
        TextInputLayout usernameTextInputLayout=findViewById(R.id.textInputShowroomUsername);
        TextInputLayout emailTextInputLayout=findViewById(R.id.textInputShowroomEmail);
        TextInputLayout mobileTextInputLayout=findViewById(R.id.textInputShowroomMobile);
        TextInputLayout passwordTextInputLayout=findViewById(R.id.textInputShowroomPassword);
        TextInputLayout confirmTextInputLayout=findViewById(R.id.textInputShowroomCofirm);

        Button register=findViewById(R.id.btnShowBtnRegister);



        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Showroom.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatTextViewDrawableApis")
            @Override
            public void onClick(View v) {

                //For the Showroom
                if (TextUtils.isEmpty(showroomName.getText().toString())){
                    showroomNameText.setText("Enter Showroom");
                    showroomNameText.setVisibility(View.VISIBLE);
                    showroomName.requestFocus();
                    return;
                } else {
                    showroomNameText.setVisibility(View.GONE);


                }
                //For the  Username
                if (TextUtils.isEmpty(usernameShow.getText().toString())){
                    usernameShowText.setText("Enter Username");
                    usernameShowText.setVisibility(View.VISIBLE);
                    usernameShow.requestFocus();
                    return;
                } else {
                    usernameShowText.setVisibility(View.GONE);
                }

                //For enter  the Email
                if (TextUtils.isEmpty(emailShow.getText().toString())) {
                    emailShowText.setText("Enter Email");
                    emailShowText.setVisibility(View.VISIBLE);
                    emailShow.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailShow.getText().toString()).matches()) {
                    emailShowText.setText("Enter Valid email");
                    emailShowText.setVisibility(View.VISIBLE);
                    emailShow.requestFocus();
                    return;
                }
               else {
                    showroomNameText.setVisibility(View.GONE);
                }

                //For the mobile number
                if(TextUtils.isEmpty(mobileShow.getText().toString())){
                    mobileShowText.setText("Enter Mobile Number");
                    mobileShowText.setVisibility(View.VISIBLE);
                    mobileShow.requestFocus();
                    return;
                }
                else if (mobileShow.getText().toString().length()!= 10) {
                    mobileShowText.setText("Enter Valid Number");
                    mobileShowText.setVisibility(View.VISIBLE);
                    mobileShow.requestFocus();
                    return;
                }else if (mobileShow.getText().toString().length()== 10){
                    showroomNameText.setVisibility(View.GONE);
                }

                //For the Password
                if (TextUtils.isEmpty(passwordShow.getText().toString())){
                    passwordShowText.setText("Enter Password");
                    passwordShowText.setVisibility(View.VISIBLE);
                    passwordShow.requestFocus();
                    return;
                } else {
                    passwordShowText.setVisibility(View.GONE);
                }

                //For the verify comfirm password is match or not.
                if(!passwordShow.getText().toString().equals(confShow.getText().toString())){
                    passwordErrorTextView.setText("Confirm Password not match");
                    passwordErrorTextView.setVisibility(View.VISIBLE);
                    confShow.requestFocus();
                    return;
                } else {
                    passwordErrorTextView.setVisibility(View.GONE);
                }


                HashMap<String,String> param=new HashMap<String, String>();
                param.put("showroom_name",showroomName.getText().toString());
                param.put("username",usernameShow.getText().toString());
                param.put("email",emailShow.getText().toString());
                param.put("mobile",mobileShow.getText().toString());
                param.put("password",passwordShow.getText().toString());

                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                String rs= Network.connect("http://"+Network.IP+"/showroomRegister.php",param);
                if (rs.equals("0")){

                    Toast.makeText(ShowroomRegisteration.this, "Failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ShowroomRegisteration.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

}