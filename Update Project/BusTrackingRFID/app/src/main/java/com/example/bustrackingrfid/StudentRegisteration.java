package com.example.bustrackingrfid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
//import com.hbb20.CountryCodePicker;

public class StudentRegisteration extends AppCompatActivity {


FirebaseAuth auth=FirebaseAuth.getInstance();
Long timeoutSeconds=60L;
String verificationCode;
PhoneAuthProvider.ForceResendingToken  resendingToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        HashMap<String, String> param = new HashMap<String, String>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

//        String rs= Network.connect("http://"+Network.IP+"/getRFID.php",param);

        EditText studName = findViewById(R.id.editStudName);
        EditText rfid = findViewById(R.id.editRFID);
        EditText address = findViewById(R.id.editStudAddress);
        EditText mobileStud = findViewById(R.id.editStudMobileNo);
        EditText mobileParent = findViewById(R.id.editParentMobileNo);
        rfid.setEnabled(false);
//        rfid.setText(rs.toString());
        rfid.setText("12456");

        TextView studNameText = findViewById(R.id.textShowroom);
        TextView rfidText = findViewById(R.id.textUsername);
        TextView addressText = findViewById(R.id.textShowEmail);
        TextView mobileStudText = findViewById(R.id.textShowMobileNo);
        TextView mobileParentText = findViewById(R.id.textParentMobileNo);

        TextInputLayout showTextInputLayout = findViewById(R.id.textInputShowroom);
        TextInputLayout usernameTextInputLayout = findViewById(R.id.textInputShowroomUsername);
        TextInputLayout emailTextInputLayout = findViewById(R.id.textInputShowroomEmail);
        TextInputLayout mobileTextInputLayout = findViewById(R.id.textInputShowroomMobile);
        TextInputLayout passwordTextInputLayout = findViewById(R.id.textInputStudMobile);


        Button register = findViewById(R.id.btnShowBtnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatTextViewDrawableApis")
            @Override
            public void onClick(View v) {

                //for the Parent Mobile
                if (TextUtils.isEmpty(mobileParent.getText().toString())) {
                    mobileParentText.setText("Enter Parent Mobile Number");
                    mobileParentText.setVisibility(View.VISIBLE);
                    mobileParent.requestFocus();

                } else if (mobileParent.getText().toString().length() != 10) {
                    mobileParentText.setText("Enter Valid Number");
                    mobileParentText.setVisibility(View.VISIBLE);
                    mobileParent.requestFocus();
                    return;

                } else if (mobileParent.getText().toString().length() == 10) {
                    mobileParentText.setVisibility(View.GONE);
                }

                //For the Student mobile number
                if (TextUtils.isEmpty(mobileStud.getText().toString())) {
                    mobileStudText.setText("Enter Student Mobile Number");
                    mobileStudText.setVisibility(View.VISIBLE);
                    mobileStud.requestFocus();

                } else if (mobileStud.getText().toString().length() != 10) {
                    mobileStudText.setText("Enter Valid Number");
                    mobileStudText.setVisibility(View.VISIBLE);
                    mobileStud.requestFocus();
                    return;
                } else if (mobileStud.getText().toString().length() == 10) {
                    mobileStudText.setVisibility(View.GONE);
                }


                //For enter  the address
                if (TextUtils.isEmpty(address.getText().toString())) {
                    addressText.setText("Enter Address");
                    addressText.setVisibility(View.VISIBLE);
                    address.requestFocus();

                } else {
                    addressText.setVisibility(View.GONE);
                }


                //For the   RFId
                if (TextUtils.isEmpty(rfid.getText().toString())) {
                    rfidText.setText("Enter RFID");
                    rfidText.setVisibility(View.VISIBLE);
                    rfid.requestFocus();

                } else {
                    rfidText.setVisibility(View.GONE);
                }
                //For the Student Name
                if (TextUtils.isEmpty(studName.getText().toString())) {
                    studNameText.setText("Enter Student Name");
                    studNameText.setVisibility(View.VISIBLE);
                    studName.requestFocus();

                } else {
                    studNameText.setVisibility(View.GONE);


                }


                String name = studName.getText().toString().trim();
                String rfids = rfid.getText().toString().trim();
                String add = address.getText().toString().trim();
                String studM = mobileStud.getText().toString().trim();
                String parentM = mobileParent.getText().toString().trim();

//                countryCodePicker.registerCarrierNumberEditText(parentM);

                if ((name.isEmpty() || rfids.isEmpty() || add.isEmpty() || studM.isEmpty() || parentM.isEmpty())) {

                    Toast.makeText(StudentRegisteration.this, "Please enter all the details", Toast.LENGTH_SHORT).show();

                } else {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("student_nm", studName.getText().toString());
                    param.put("rfid", rfid.getText().toString());
                    param.put("address", address.getText().toString());
                    param.put("stud_mobile", mobileStud.getText().toString());
                    param.put("parent_mobile", mobileParent.getText().toString());

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    String rs = Network.connect("http://" + Network.IP + "/studentRegistration.php", param);
                    if (rs.equals("0")) {

                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), LoginOtp.class);
                        String num = (mobileParent.getText().toString().trim());
                        intent.putExtra("number",num);
                        startActivity(intent);
                        finish();
                    }
                }
            }

        });


    }
}
